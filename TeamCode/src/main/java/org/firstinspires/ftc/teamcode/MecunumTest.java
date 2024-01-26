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
            
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

            leftFront.setPower((drive + strafe + twist) / max);
            rightFront.setPower((drive - strafe - twist) / max);
            leftBack.setPower((drive - strafe + twist) / max);
            rightBack.setPower((drive + strafe - twist) / max);
            telemetry.addData("Left Trigger: ", gamepad2.left_trigger);
            telemetry.addData("Right Trigger: ", gamepad2.right_trigger);
            telemetry.update();
        }
    }

}
