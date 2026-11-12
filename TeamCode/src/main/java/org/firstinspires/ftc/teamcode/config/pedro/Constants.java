package org.firstinspires.ftc.teamcode.config.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.config.pedro.procedures.Tests;

public class Constants {


    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);

        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.manualBrakeMode.set(true);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(0.09998302760086661);
        c.yPodOffset.set(-3.585457689180149);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

//    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
//        c.name.set("pinpoint");
//        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
////        c.xPodOffset.set(2.569707134577233);
////        c.yPodOffset.set(-2.676946084330401);
//
//        c.xPodOffset.set(-4.025);
//        c.yPodOffset.set(2.875);
//        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
//        c.globalDistanceUnit.set(DistanceUnit.INCH);
//        c.offsetUnits.set(DistanceUnit.INCH);
//    });

public static ForesightConfig foresightConfig = new ForesightConfig(
        c -> {
            Controller primaryTranslationalForward = Controller.proportional(0.2547816670056667);
            Controller secondaryTranslationalForward = Controller.proportional(0.09413501806706871);
            Controller primaryTranslationalLateral = Controller.proportional(0.38281164236181414);
            Controller secondaryTranslationalLateral = Controller.proportional(0.14143867293721782);

            c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
            c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

            c.coast.set(Controller.proportionalFeedforward(0.012204935052620379));
            c.brake.set(Controller.proportionalFeedforward(0.010374194794727322));

            c.headingFeedback.set(Controller.proportional(3.726652991455102));
            c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0513827177235779, 0.005367357469669862));

            c.linearBrakeCoefficients.set(Matrix.diag(0.06370885738652173, 0.04642908929061408));
            c.quadraticBrakeCoefficients.set(Matrix.diag(0.002004421101220326, 0.002048741838964872));

            c.maxAchievableForwardVelocity.set(78.58685457202002);
            c.maxAchievableStrafeVelocity.set(64.93758850150131);
            c.naturalForwardDeceleration.set(31.315698373921293);
            c.naturalStrafeDeceleration.set(59.51798788407373);
        }
);

    @Tuner
    public static Procedure tests() {
        return new Tests(hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig), (hardwareMap -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig)), () -> new Foresight(Constants.foresightConfig));
    }

    public static Follower create(HardwareMap h){
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }

}