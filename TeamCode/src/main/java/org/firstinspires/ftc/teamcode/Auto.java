package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@Autonomous(name = "Auto")
public class Auto extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        double timer1 = 1.25;
        double timer2 = 2 * timer1 + 0.05;
        double timer3 = timer2 + 0.2;
        double timer4 = + timer3 + 5.0;

        DcMotorSimple leftFront = hardwareMap.get(DcMotorSimple.class, "frontLeft");
        DcMotorSimple leftBack = hardwareMap.get(DcMotorSimple.class, "backLeft");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "frontRight");
        DcMotorSimple rightBack = hardwareMap.get(DcMotorSimple.class, "backRight");

        Servo clawR = hardwareMap.get(Servo.class, "clawR");
        Servo clawL = hardwareMap.get(Servo.class, "clawL");
        Servo pivotR = hardwareMap.get(Servo.class, "pivotR");
        CRServo wrist = hardwareMap.get(CRServo.class, "wrist");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        ElapsedTime elapseTimer = new ElapsedTime();


        waitForStart();

        while (opModeIsActive()) {

            double drive = 0;
            double strafe = 0;
            double twist = 0;

            // Claw R open 0.005
            // Claw Close 0.24
            // 0.73 Wrist Up
            // 0.43 Wrist Down
            // Pivot Up 0.65
            // Pivot Down 0.97

//            if (gamepad2.left_bumper)
//            {
//                pivotR.setPosition(0.3);
//            }
//            if (gamepad2.right_bumper)
//            {
//                pivotR.setPosition(0.98);
//            }
//
//            wrist.setPower(gamepad2.left_stick_y * 0.1);
//
//
//            if (gamepad2.triangle)
//            {
//                clawR.setPosition(0.005);
//                clawL.setPosition(0.5);
//            }
//            else
//            {
//                clawR.setPosition(0.24);
//                clawL.setPosition(0.24);
//            }

            if (elapseTimer.time() < timer1)
            {
                drive = 0.5;
            }

            else if (elapseTimer.time() < timer2)
            {
                drive = -0.5;
            }

            else if (elapseTimer.time() < timer3)
            {
                drive = 0.5;
            }

            else if (elapseTimer.time() < timer4)
            {
                strafe = 0.5;
            }

            double max = Math.max(Math.abs(strafe) + Math.abs(drive) + Math.abs(twist), 1);

            leftFront.setPower((drive + strafe + twist) / max);
            leftBack.setPower((drive - strafe + twist) / max);
            rightFront.setPower((drive - strafe - twist) / max);
            rightBack.setPower((drive + strafe - twist) / max);

            telemetry.update();
        }
    }
}
