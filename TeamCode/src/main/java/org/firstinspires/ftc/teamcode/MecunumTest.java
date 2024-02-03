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
        double servoPos = 0.5;
        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "frontLeft");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "backLeft");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "frontRight");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "backRight");

        Servo clawR = hardwareMap.get(Servo.class, "clawR");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double leftY = gamepad1.left_stick_y;
            double leftX = -gamepad1.left_stick_x;
            double rightX = -gamepad1.right_stick_x;

            double drive = leftY;
            double strafe = leftX * 1.1;
            double twist = rightX;

            double max = Math.max(Math.abs(leftX) + Math.abs(leftY) + Math.abs(rightX), 1);

            leftFront.setPower((drive + strafe + twist) / max);
            rightFront.setPower((drive - strafe - twist) / max);
            leftBack.setPower((drive - strafe + twist) / max);
            rightBack.setPower((drive + strafe - twist) / max);

            if (gamepad1.left_bumper)
            {
                servoPos -= 0.001;
            }
            if (gamepad1.right_bumper)
            {
                servoPos += 0.001;
            }
            clawR.setPosition(servoPos);

            telemetry.addData("Servo Position: ", servoPos);

            telemetry.update();
        }
    }

}
