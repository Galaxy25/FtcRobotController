package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "NateTest")
public class main extends LinearOpMode
{
    double up;
    DcMotor motor;
    @Override
    public void runOpMode()
    {
        motor = hardwareMap.get(DcMotor.class, "motor1");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Initialized", "Successful");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            up = -this.gamepad1.left_stick_y;
            motor.setPower(up);
            telemetry.addData("Joystick", String.valueOf(up));
            telemetry.update();

        }
    }
}
