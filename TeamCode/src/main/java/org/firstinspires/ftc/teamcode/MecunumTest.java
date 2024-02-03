package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumTest")
public class MecunumTest extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "frontLeft");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "backLeft");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "frontRight");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "backRight");

        Servo clawR = hardwareMap.get(Servo.class, "clawR");
        Servo clawL = hardwareMap.get(Servo.class, "clawL");
        Servo pivotR = hardwareMap.get(Servo.class, "pivotR");
        CRServo wrist = hardwareMap.get(CRServo.class, "wrist");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {
            double leftY = gamepad1.left_stick_y;
            double leftX = -gamepad1.left_stick_x;
            double rightX = -gamepad1.right_stick_x;

            double drive = leftY;
            double strafe = leftX;
            double twist = rightX;

            double max = Math.max(Math.abs(strafe) + Math.abs(drive) + Math.abs(twist), 1);

            leftFront.setPower((drive + strafe + twist) / max);
            leftBack.setPower((drive - strafe + twist) / max);
            rightFront.setPower((drive - strafe - twist) / max);
            rightBack.setPower((drive + strafe - twist) / max);

            // Claw R open 0.005
            // Claw Close 0.24
            // 0.73 Wrist Up
            // 0.43 Wrist Down
            // Pivot Up 0.65
            // Pivot Down 0.97

            if (gamepad2.left_bumper)
            {
                pivotR.setPosition(0.3);
            }
            if (gamepad2.right_bumper)
            {
                pivotR.setPosition(0.98);
            }

            wrist.setPower(gamepad2.left_stick_y * 0.1);


            if (gamepad2.triangle)
            {
                clawR.setPosition(0.005);
                clawL.setPosition(0.5);
            }
            else
            {
                clawR.setPosition(0.24);
                clawL.setPosition(0.24);
            }


            telemetry.update();
        }
    }

}
