// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package com.team2502.robot2026

import com.ctre.phoenix6.swerve.SwerveModule
import com.ctre.phoenix6.swerve.SwerveRequest
import com.team2502.lib.TMJoystick
import com.team2502.robot2026.Constants.OI
import com.team2502.robot2026.commands.runIntakeCommand
import com.team2502.robot2026.commands.runOuttakeCommand
import com.team2502.robot2026.subsystems.Drive.CommandSwerveDrivetrain
import com.team2502.robot2026.subsystems.VisionSubsystem
import com.team2502.robot2026.subsystems.IntakeSubsystem
import com.team2502.robot2026.subsystems.Drive.TunerConstants
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj.DriverStation
import java.util.concurrent.atomic.AtomicReference
import kotlin.jvm.optionals.getOrDefault
import com.team2502.robot2026.subsystems.ShooterSubsystem


class RobotContainer {
    // Joystick mappings
    val driverLeft: TMJoystick = TMJoystick(OI.JOYSTICK_DRIVE_LEFT)
    val driverRight: TMJoystick = TMJoystick(OI.JOYSTICK_DRIVE_RIGHT)
    val operator: TMJoystick = TMJoystick(OI.JOYSTICK_OPERATOR)

    // Subsystems
    val drivetrainSubsystem: CommandSwerveDrivetrain = TunerConstants.createDrivetrain()
    val intakeSubsystem: IntakeSubsystem = IntakeSubsystem()
    val ShooterSubsystem: ShooterSubsystem = ShooterSubsystem()


    // setup for global subsystem access
    init {
        INSTANCE = this
    }

    // separate from init b/c init needs to execute first
    fun initialize() {
        configureBindings()
    }

    fun configureBindings() {
        // Drivetrain bindings
        drivetrainSubsystem.defaultCommand = drivetrainSubsystem.applyRequest {
            val alliance = DriverStation.getAlliance().getOrDefault(DriverStation.Alliance.Blue)
            var velX: Double
            var velY: Double
            val rotationRate = -driverRight.z
            // NEEDS TO RENABLE TELEOP FOR ALLIANCE TO FLIP
            velX = driverLeft.y
            velY = -driverLeft.x
            SwerveRequest.FieldCentric()
                .withDeadband(OI.TRANSLATION_DEADBAND_METERS_PER_SECOND)
                .withRotationalDeadband(OI.ROTATION_DEADBAND_RADIANS_PER_SECOND)
                .withDriveRequestType(SwerveModule.DriveRequestType.Velocity)
                .withVelocityX(velX * Constants.Drivetrain.MEASURED_MAX_SPEED_METERS_PER_SECOND)
                .withVelocityY(velY * Constants.Drivetrain.MEASURED_MAX_SPEED_METERS_PER_SECOND)
                .withRotationalRate(rotationRate * Constants.Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
        }

        // Bindings
        operator.leftThumbButton().whileTrue(runIntakeCommand())
        operator.middleThumbButton().whileTrue(runOuttakeCommand())

//        driverLeft.middleThumbButton().whileTrue(setTurretCommand(Rotation2d(0.0)))
//        driverLeft.rightThumbButton().whileTrue(setTurretFieldAngleCommand(Rotation2d(0.0)))
        // driverRight.rightThumbButton().whileTrue(VisionUpdateCommand())
    }

    fun update() {

        if (operator.frontTrigger().equals(true)){
            ShooterSubsystem.isShooting = true
        }
        else{
            ShooterSubsystem.isShooting = false
        }
    }

    // global subsystem access via companion object
    companion object {
        private val INSTANCE_CONTAINER = AtomicReference<RobotContainer>()
        var INSTANCE: RobotContainer
            set(value) {
                INSTANCE_CONTAINER.set(value)
            }
            get() {
                return INSTANCE_CONTAINER.get()
            }
    }

    fun getPose(): Pose2d {
        val ret = drivetrainSubsystem.getPose()
        return Pose2d(ret.x, ret.y, getYaw())
    }

    fun getYaw(): Rotation2d {
        return Rotation2d.fromDegrees(drivetrainSubsystem.pigeon2.yaw.valueAsDouble) + VisionSubsystem.pigeonZero
    }

    fun getVelocity(): ChassisSpeeds {
        val robotSpeeds = drivetrainSubsystem.state.Speeds
        return ChassisSpeeds.fromRobotRelativeSpeeds(robotSpeeds, getYaw())
    }

    fun getCommandedVelocity(): ChassisSpeeds {
        val state = drivetrainSubsystem.getStateCopy()
        val robotRelative = drivetrainSubsystem.kinematics.toChassisSpeeds(*state.ModuleTargets)
        return ChassisSpeeds.fromRobotRelativeSpeeds(robotRelative, getYaw())
    }

}
