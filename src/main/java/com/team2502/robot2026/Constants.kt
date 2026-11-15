package com.team2502.robot2026

class Constants {
    object OI {
        const val JOYSTICK_DRIVE_LEFT: Int = 0
        const val JOYSTICK_DRIVE_RIGHT: Int = 1
        const val JOYSTICK_OPERATOR: Int = 2

        const val TRANSLATION_DEADBAND_METERS_PER_SECOND: Double = 0.5
        const val ROTATION_DEADBAND_RADIANS_PER_SECOND: Double = 0.1
    }

    object Drivetrain {
        const val MEASURED_MAX_SPEED_METERS_PER_SECOND: Double = 4.58
        const val MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND: Double = 2 * 3.1415
    }

    object Intake {
        const val INTAKE_IN_DUTY_CYCLE_RIGHT: Double = 1.0
        const val INTAKE_IN_DUTY_CYCLE_LEFT: Double = 1.0
        const val INTAKE_OUT_DUTY_CYCLE_RIGHT: Double = -1.0
        const val INTAKE_OUT_DUTY_CYCLE_LEFT: Double = -1.0
    }

    object Shooter {
        const val INDEXER_FEED_DUTY_CYCLE_RIGHT: Double = 1.0
        const val INDEXER_FEED_DUTY_CYCLE_LEFT: Double = 1.0
        const val INDEXER_PASSIVE_DUTY_CYCLE_RIGHT: Double = 0.1
        const val INDEXER_PASSIVE_DUTY_CYCLE_LEFT: Double = 0.1
        const val MIN_VELOCITY: Double = 40.0
        const val MAX_VELOCITY: Double = 320.0

    }

    object Localization {
        const val LIMELIGHT_FRONT_NAME: String = "limelight-front"
        const val MAX_LIMELIGHT_POSE_DIFFERENCE_METERS = 1.0
        const val ACCEPTABLE_OUTLIER_COUNT = 4
        const val POSE_ANTICIPATION_TIMESTEP_SECS = 0.1
        const val YAW_ANTICIPATION_TIMESTEP_SECS = 0.05
    }

    /// Choreo coordinates: blue right is (0,0), towards red is +x
    object Field {
        const val HALF_FIELD_WIDTH_METERS = 8.042656 / 2.0 /// y
        const val HALF_FIELD_LENGTH_METERS = 16.513048 / 2.0 /// x

        const val BLUE_HUB_X_METERS = 4.625594
        const val BLUE_HUB_Y_METERS = 4.034536
        const val RED_HUB_X_METERS = 11.915394
        const val RED_HUB_Y_METERS = 4.034536

        const val RED_TOP_PASS_OFFSET_X_METERS = 3.0
        const val RED_TOP_PASS_OFFSET_Y_METERS = 2.01
        const val RED_BOTTOM_PASS_OFFSET_X_METERS = 3.0
        const val RED_BOTTOM_PASS_OFFSET_Y_METERS = -2.01
        const val BLUE_TOP_PASS_OFFSET_X_METERS = -3.0
        const val BLUE_TOP_PASS_OFFSET_Y_METERS = 2.01
        const val BLUE_BOTTOM_PASS_OFFSET_X_METERS = -3.0
        const val BLUE_BOTTOM_PASS_OFFSET_Y_METERS = -2.01
    }

    object Weights {
        const val COMMANDED_VELOCITY_WEIGHT = 0.65
        const val GYRO_EMA_WEIGHT = 0.01
        const val VISION_HEADING_STD_DEV_HARDCODE = 0.01
        const val CHASSIS_XY_STDDEV_COEFFICIENT = 0.04
        const val DEFAULT_XY_STDDEV = 0.25
    }
}
