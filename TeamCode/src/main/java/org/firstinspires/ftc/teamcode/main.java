package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "NateTest")
public class main extends LinearOpMode
{
    DcMotor motor;
    @Override
    public void runOpMode()
    {
        telemetry.addData("Initilized", "Successful");
        telemetry.update();
    }
}
