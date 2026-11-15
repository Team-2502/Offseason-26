package com.team2502.robot2026.commands

import com.team2502.robot2026.RobotContainer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands.runEnd
import edu.wpi.first.wpilibj2.command.Commands.runOnce

fun runIntakeCommand() : Command {
    val intakeSubsystem = RobotContainer.INSTANCE.intakeSubsystem

    return runEnd(
        intakeSubsystem::intake,
        intakeSubsystem::stop,
        intakeSubsystem,
    )
}

fun runOuttakeCommand() : Command {
    val intakeSubsystem = RobotContainer.INSTANCE.intakeSubsystem
    return runEnd(
        intakeSubsystem::outtake,
        intakeSubsystem::stop,
        intakeSubsystem,
    )
}

fun stopIntakeCommand() : Command {
    val intakeSubsystem = RobotContainer.INSTANCE.intakeSubsystem

    return runOnce(intakeSubsystem::stop)
}
