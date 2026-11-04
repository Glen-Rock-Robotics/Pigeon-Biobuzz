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
        c.xPodOffset.set(2.798568845733883);
        c.yPodOffset.set(-3.928397171140656);
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
                Controller primaryTranslationalForward = Controller.proportional(0.2036434300385757);
                Controller secondaryTranslationalForward = Controller.proportional(0.07524080594658643);
                Controller primaryTranslationalLateral = Controller.proportional(0.3013964177327431);
                Controller secondaryTranslationalLateral = Controller.proportional(0.11135792289164405);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.017170360368943198));
                c.brake.set(Controller.proportionalFeedforward(0.014594806313601718));

                c.headingFeedback.set(Controller.proportional(3.0689184776808696));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04643394780985823, 0.00481519030055644));

                c.linearBrakeCoefficients.set(Matrix.diag(0.0903704498767139, 0.08046941388342924));
                c.quadraticBrakeCoefficients.set(Matrix.diag(7.134174010940169E-4, 9.148262691730635E-4));

                c.maxAchievableForwardVelocity.set(57.07634109236024);
                c.maxAchievableStrafeVelocity.set(46.820684392839006);
                c.naturalForwardDeceleration.set(43.164470306552815);
                c.naturalStrafeDeceleration.set(64.68614979874148);
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