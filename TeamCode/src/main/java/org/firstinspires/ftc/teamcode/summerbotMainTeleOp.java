package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by AHSRobotics on 7/24/2017.
 */
@TeleOp(name = "Summerbot TeleOp", group = "Summer")

public class summerbotMainTeleOp extends OpMode implements Runnable{
    private DcMotor leftMotor, rightMotor;
    private enum CState{
        Normal, SlowSpeed
    }
    private CState cState;

    @Override
    public void run(){

    }

    @Override
    public void init(){
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cState = CState.Normal;

    }
    @Override
    public void start(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);


    }
    @Override
    public void stop(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }
    @Override
    public void loop() {
        float throttle = -gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;
        float right = throttle + direction;
        float left = throttle - direction;
        motorPower(left, right);
    }
    private void motorPower(float left, float right){
        if(cState == CState.Normal) {
            leftMotor.setPower(left);
            rightMotor.setPower(right);
        }
        if(cState == CState.SlowSpeed){
            leftMotor.setPower(left*.5);
            rightMotor.setPower(right*.5);
        }
    }


}

