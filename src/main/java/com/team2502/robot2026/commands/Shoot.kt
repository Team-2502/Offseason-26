package com.team2502.robot2026.commands

import com.team2502.robot2026.RobotContainer
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands.runEnd
import edu.wpi.first.wpilibj2.command.Commands.runOnce
import com.team2502.robot2026.subsystems.ShooterSubsystem
import edu.wpi.first.wpilibj.Timer.delay
import java.util.function.DoubleSupplier

fun ShootCommand(input: Double): Command {

    val ShooterSubsystem = RobotContainer.INSTANCE.shooterSubsystem

    val duty = input
    println("duty: $duty")
    return runEnd(
        {
            ShooterSubsystem.set(
                duty,
                duty
            )
        }, ShooterSubsystem::stop, ShooterSubsystem
    )
}
