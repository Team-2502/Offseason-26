package com.team2502.robot2026.commands

import com.team2502.robot2026.RobotContainer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands.runEnd
import edu.wpi.first.wpilibj2.command.Commands.runOnce
import com.team2502.robot2026.subsystems.ShooterSubsystem

fun ShootCommand(): Command {

    val ShooterSubsystem = RobotContainer.INSTANCE.ShooterSubsystem
    if (ShooterSubsystem.isShooting == true) {

        return runEnd(
            ShooterSubsystem::feed,
            ShooterSubsystem::stop,
            ShooterSubsystem

        )
    } else{
        return runEnd(
            ShooterSubsystem::passive,
            ShooterSubsystem::stop,
            ShooterSubsystem
        )
    }
}
