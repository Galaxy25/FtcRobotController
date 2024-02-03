package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "DirectionTest")
public class WheelDirectionTest extends LinearOpMode {
    public void runOpMode() {
        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "frontLeft");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "backLeft");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "frontRight");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "backRight");


        waitForStart();

        while (opModeIsActive())
        {
            if(gamepad1.left_bumper)
            {
                leftFront.setPower(0.5);
            }
            if (gamepad1.square)
            {
                leftBack.setPower(0.5);
            }
            if (gamepad1.right_bumper)
            {
                rightFront.setPower(0.5);
            }
            if (gamepad1.circle)
            {
                rightBack.setPower(0.5);
            }
        }
    }
}
