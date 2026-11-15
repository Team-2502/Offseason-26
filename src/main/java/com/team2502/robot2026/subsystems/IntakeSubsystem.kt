package com.team2502.robot2026.subsystems

import com.ctre.phoenix6.controls.DutyCycleOut
import edu.wpi.first.wpilibj2.command.SubsystemBase
import com.team2502.lib.config.create
import com.team2502.robot2026.RobotCAN.INTAKE_LEFT
import com.team2502.robot2026.RobotCAN.INTAKE_RIGHT
import com.team2502.robot2026.Constants.Intake.INTAKE_IN_DUTY_CYCLE_RIGHT
import com.team2502.robot2026.Constants.Intake.INTAKE_OUT_DUTY_CYCLE_RIGHT
import com.team2502.robot2026.Constants.Intake.INTAKE_IN_DUTY_CYCLE_LEFT
import com.team2502.robot2026.Constants.Intake.INTAKE_OUT_DUTY_CYCLE_LEFT

class IntakeSubsystem : SubsystemBase() {
    private val intakeMotorLeft = INTAKE_LEFT.create()
    private val intakeMotorRight = INTAKE_RIGHT.create()
    private val intakeDutyCycleRequest = DutyCycleOut(0.0)


    private fun setDutyCycle(dutyCycleIntakeLeft: Double, dutyCycleIntakeRight: Double) {
        intakeMotorLeft.setControl(intakeDutyCycleRequest.withOutput(dutyCycleIntakeLeft))
        intakeMotorRight.setControl(intakeDutyCycleRequest.withOutput(dutyCycleIntakeRight))
    }

    fun intake(){
        setDutyCycle(
            INTAKE_IN_DUTY_CYCLE_RIGHT,
            INTAKE_IN_DUTY_CYCLE_LEFT
        )
    }

    fun outtake(){
        setDutyCycle(
            INTAKE_OUT_DUTY_CYCLE_RIGHT,
            INTAKE_OUT_DUTY_CYCLE_LEFT
        )
    }

    fun stop(){
            intakeMotorLeft.stopMotor()
            intakeMotorRight.stopMotor()
    }

}