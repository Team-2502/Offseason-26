package com.team2502.robot2026.subsystems

import com.ctre.phoenix6.controls.DutyCycleOut
import com.team2502.lib.config.create
import com.team2502.robot2026.RobotContainer
import com.team2502.robot2026.Constants.Shooter.INDEXER_FEED_DUTY_CYCLE_LEFT
import com.team2502.robot2026.Constants.Shooter.INDEXER_FEED_DUTY_CYCLE_RIGHT
import com.team2502.robot2026.Constants.Shooter.INDEXER_PASSIVE_DUTY_CYCLE_LEFT
import com.team2502.robot2026.Constants.Shooter.INDEXER_PASSIVE_DUTY_CYCLE_RIGHT
import com.team2502.robot2026.RobotCAN.INDEXER_LEFT
import com.team2502.robot2026.RobotCAN.INDEXER_RIGHT
import com.team2502.robot2026.RobotCAN.SHOOTER_LEFT
import com.team2502.robot2026.RobotCAN.SHOOTER_RIGHT
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.wpilibj2.command.SubsystemBase


class ShooterSubsystem : SubsystemBase() {
    private val shooterLeftMotor = SHOOTER_LEFT.create()
    private val shooterRightMotor = SHOOTER_RIGHT.create()
    private val indexerLeftMotor = INDEXER_LEFT.create()
    private val indexerRightMotor = INDEXER_RIGHT.create()
    private val shootDutyCycleRequest = DutyCycleOut(0.0)
    var isShooting: Boolean = false


    private fun setDutyCycleFeed(dutyCycleIndexerLeft: Double, dutyCycleIndexerRight: Double,) {
        indexerLeftMotor.setControl(shootDutyCycleRequest.withOutput(dutyCycleIndexerLeft))
        indexerRightMotor.setControl(shootDutyCycleRequest.withOutput(dutyCycleIndexerRight))
    }

    private fun setDutyCyclePassive(dutyCycleIndexerLeft: Double, dutyCycleIndexerRight: Double,) {
        indexerLeftMotor.setControl(shootDutyCycleRequest.withOutput(dutyCycleIndexerLeft))
        indexerRightMotor.setControl(shootDutyCycleRequest.withOutput(dutyCycleIndexerRight))
    }

    fun feed(){
        setDutyCycleFeed(
            INDEXER_FEED_DUTY_CYCLE_LEFT,
            INDEXER_FEED_DUTY_CYCLE_RIGHT,

        )
    }


    fun passive(){
        setDutyCyclePassive(
            INDEXER_PASSIVE_DUTY_CYCLE_LEFT,
            INDEXER_PASSIVE_DUTY_CYCLE_RIGHT
        )
    }

    fun stop(){
        indexerLeftMotor.stopMotor()
        indexerRightMotor.stopMotor()
    }

}