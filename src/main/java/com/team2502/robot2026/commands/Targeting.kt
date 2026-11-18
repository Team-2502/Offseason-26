package com.team2502.robot2026.commands

import com.team2502.robot2026.Constants
import com.team2502.robot2026.RobotContainer
import com.team2502.robot2026.subsystems.ShooterSubsystem
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands.runEnd
import edu.wpi.first.wpilibj2.command.Commands.runOnce
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.abs
import kotlin.math.sqrt
import com.ctre.phoenix6.swerve.SwerveModule
import com.ctre.phoenix6.swerve.SwerveRequest

class hubPose (val x: Float, val y: Float)
    val blueHubPose = hubPose(4.62032413482666f,4.034935474395752f)
    val redHubPose = hubPose(11.917652130126953f, 4.030294418334961f)
    val robotPose = RobotContainer.INSTANCE.getPose()

fun aimCommand() : Command {
    val drivetrain = RobotContainer.INSTANCE.drivetrainSubsystem
    val controller = PIDController(0.5, 0.0, 0.0) //NEED TUNING, THESE ARE PLACEHOLDERS


    val alliance = DriverStation.getAlliance()?: DriverStation.Alliance.Blue
    val target = if (alliance == DriverStation.Alliance.Blue)
        {blueHubPose}
    else redHubPose


    val aimAngle = atan2(blueHubPose.y - robotPose.y, blueHubPose.x - robotPose.x)

    val gain = controller.calculate(robotPose.rotation.radians, aimAngle)

    return drivetrain.applyRequest ( {SwerveRequest.FieldCentric()
        .withDriveRequestType(SwerveModule.DriveRequestType.Velocity)
        .withVelocityX(0 * Constants.Drivetrain.MEASURED_MAX_SPEED_METERS_PER_SECOND)
        .withVelocityY(0 * Constants.Drivetrain.MEASURED_MAX_SPEED_METERS_PER_SECOND)
        .withRotationalRate(gain * Constants.Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
    })

}

fun setFlywheelSpeedCommand() : Command {
    val shooterSubsystem = RobotContainer.INSTANCE.shooterSubsystem
    val deltaXdistance = blueHubPose.x - robotPose.x
    val deltaYdistance = redHubPose.y - robotPose.y
    val deltaZdistance = abs(sqrt(deltaXdistance.pow(2) + deltaYdistance.pow(2))) //pythagorean theorm for the win
    val shooterCoeffiecent = 1 //made up, just a placeholder, tune later //y=Mx+b
    val shooterNumber = 1 //made up, just a placeholder, tune later // y=mx+B
    var flywheelSpeed = shooterCoeffiecent * deltaZdistance + shooterNumber

    return runOnce({shooterSubsystem.setDutyCycleShooter(flywheelSpeed, flywheelSpeed)}, shooterSubsystem)
    }
