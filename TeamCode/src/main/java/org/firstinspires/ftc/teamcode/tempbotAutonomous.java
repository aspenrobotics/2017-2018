package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



//import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "tempsummerauto", group = "temp")
public class tempbotAutonomous extends LinearOpMode{

    DcMotor leftMotor, rightMotor;


    public void runOpMode(){
        variableSettingsAndInitialization();
        waitForStart();
        moveRobot(.3, .3, 5500, 5500);
    }

    private void moveRobot (double leftPower, double rightPower, int leftCount,  int rightCount){
        resetEncoders(leftMotor);
        resetEncoders(rightMotor);
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
        leftMotor.setTargetPosition(leftCount);
        rightMotor.setTargetPosition(rightCount);
        while(leftMotor.isBusy() || rightMotor.isBusy()){}
        ElapsedTime time = new ElapsedTime();
        time.reset();
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }


    private void variableSettingsAndInitialization() {
        leftMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor = hardwareMap.dcMotor.get("left_drive");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        resetEncoders(rightMotor);
        resetEncoders(leftMotor);

    }
    private boolean encoderReset(DcMotor motor){
        return (motor.getCurrentPosition() == 0);
    }

    private void resetEncodersWait(DcMotor motor) throws Exception{
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Thread.sleep(30);
        if(!encoderReset(motor)){
            Thread.sleep(30);
        }
    }
    private void resetEncoders (DcMotor motor){
        try {
            resetEncodersWait(motor);
        }
        catch(Exception e){
            e.printStackTrace ();
        }
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
