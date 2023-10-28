package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "MecanumTest")
public class MecunumTest extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "leftFront");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "leftBack");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "rightBack");
        DcMotorSimple arm = hardwareMap.get(DcMotorSimple.class, "arm");
        CRServo claw = hardwareMap.get(CRServo.class, "claw");
        CRServo servoPivot = hardwareMap.get(CRServo.class, "servoPivot");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())
        {
            double leftY = gamepad1.left_stick_y;
            double leftX = gamepad1.left_stick_x;
            double rightX = gamepad1.right_stick_x;

            double drive = leftY;
            double strafe = leftX;
            double twist = rightX;

            double max = Math.max(Math.abs(leftX) + Math.abs(leftY) + Math.abs(rightX), 1);

            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

            leftFront.setPower((drive + strafe + twist) / max);
            rightFront.setPower((drive - strafe - twist) / max);
            leftBack.setPower((drive - strafe + twist) / max);
            rightBack.setPower((drive + strafe - twist) / max);
            telemetry.addData("Left Stick Y: ", gamepad1.left_stick_y);
            telemetry.addData("Left Stick X: ", gamepad1.left_stick_x);
            telemetry.addData("Right Stick X: ", gamepad1.right_stick_x);
            telemetry.addData("Right Stick X: ", gamepad1.right_stick_y);
            telemetry.update();

             // Claw opening and rotation

            if (gamepad2.left_bumper)
            {
                servoPivot.setPower(1 / 2);
            }
            else if (gamepad2.left_trigger > 0.8)
            {
                servoPivot.setPower(-1 / 2);
            }
            else
            {
                servoPivot.setPower(0);
            }

            if (gamepad2.right_bumper)
            {
                claw.setPower(1 / 2);
            }
            else if (gamepad2.right_trigger > 0.8)
            {
                claw.setPower(-1 / 2);
            }
            else
            {
                claw.setPower(0);
            }

            // Control arm speed
            if (gamepad2.left_stick_y != 0.0)
            {
                arm.setPower(gamepad2.left_stick_y / 3);
            }
        }
    }

}
