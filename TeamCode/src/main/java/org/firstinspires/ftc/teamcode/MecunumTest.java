package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())
        {
            double leftX = gamepad1.left_stick_x;
            double leftY = gamepad1.left_stick_y;
            double rightX = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(leftX) + Math.abs(leftY) + Math.abs(rightX), 1);

            leftFront.setPower((leftY + leftX + rightX) / max);
            leftBack.setPower((leftY - leftX + rightX) / max);
            rightFront.setPower((leftY - leftX - rightX) / max);
            rightBack.setPower((leftY + leftX - rightX) / max);

        }
    }

}
