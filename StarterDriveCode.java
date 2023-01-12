package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@TeleOp(name = "DriveCode", group = "")
public class StarterDriveCode extends LinearOpMode {
	//VARIABLE INITIALIZATION
    private DcMotor LeftDrive;
    private DcMotor RightDrive;
    private DcMotor ArmMotor;
    private Servo Claw;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
		//Connect Hardware Map Elements to 
        LeftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        RightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
        ArmMotor = hardwareMap.get(DcMotor.class, "Arm");
        Claw = hardwareMap.get(Servo.class, "Claw");
		
		int ArmTarget = 0;

		//Tell the telemetry that the variables were declared and initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();
		
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // run until the end of the match (driver presses STOP)
		// this section will repeat
        while (opModeIsActive()) {
            //DRIVETRAIN CODE
			
			/**
			 * This code controls the wheels of our robot
			 * When the driver pressed on the gamepad, the robot will roll forward/backwards
			 */
            
            double leftPower = gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;
            
            LeftDrive.setPower(leftPower);
            RightDrive.setPower(rightPower);
            
            //MECHANISM CODE
			
			/**
			 * This code controls the arm of our robot
			 * When the driver pressed on the direction pad, the robot will raise/lower the arm
			 */
			 
            if (gamepad1.dpad_up) {
				ArmMotor.setPower(-0.4);
			} 
			else if (gamepad1.dpad_down) {
				ArmMotor.setPower(0.2);
			}				
			else {
				ArmMotor.setPower(0);
			}
			
			/**
			 * This code controls the claw
			 * When the driver pressed on the triggers, the robot will open/close the claw
			 */
			 
			if (gamepad1.left_trigger) {
				Claw.setPosition(Claw.getPosition() + 0.25);
			}
			else if (gamepad1.right_trigger) {
				Claw.setPosition(Claw.getPosition() - 0.25);
			} 
			else {
				Claw.setPosition(0);
			}
            
            //TELEMETRY CODE
			
			/**
			 * This code controls the telemetry
			 * Every time the code runs, it will send information to the telemetry
			 */
             
			telemetry.addData("Left Drive", LeftDrive.getCurrentPosition());
			telemetry.addData("Right Drive", RightDrive.getCurrentPosition());
			telemetry.addData("Arm Position", ArmMotor.getCurrentPosition());
			telemetry.addData("Claw Action", Claw.getPosition());
			
            telemetry.update();
            }
        }
    }