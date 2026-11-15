package com.team2502.robot2026

import com.ctre.phoenix6.signals.NeutralModeValue
import com.ctre.phoenix6.signals.SensorDirectionValue
import com.team2502.lib.config.CANConfig
import com.team2502.lib.config.EncoderConfig
import com.team2502.lib.config.GyroConfig
import com.team2502.lib.config.MotorConfig
import com.team2502.lib.config.PIDConfig
import com.team2502.lib.config.RobotCANBus

object RobotCAN {
    val DRIVE_MOTOR_CONFIG =
        MotorConfig(PIDConfig(kP = 0.3), false, NeutralModeValue.Brake)
    val TURN_MOTOR_CONFIG =
        MotorConfig(PIDConfig(kP = 40.0), true, NeutralModeValue.Coast)

    val INTAKE_RIGHT = CANConfig(
        1,
        "Intake Right",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Coast),
        )

    val INTAKE_LEFT = CANConfig(
        2,
        "Intake Left",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Coast),
    )

    val INDEXER_RIGHT = CANConfig(
        3,
        "Indexer Right",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Brake),
    )

    val INDEXER_LEFT = CANConfig(
        4,
        "Indexer Left",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Brake),
    )

    val SHOOTER_RIGHT = CANConfig(
        5,
        "Shooter Right",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Brake),
    )

    val SHOOTER_LEFT = CANConfig(
        6,
        "Shooter Left",
        RobotCANBus.RIO,
        MotorConfig(PIDConfig(kP = 0.1), ccwp = false, neutralMode = NeutralModeValue.Brake),
    )

}