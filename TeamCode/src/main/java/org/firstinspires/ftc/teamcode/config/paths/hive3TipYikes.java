/*

package org.firstinspires.ftc.teamcode.config.paths;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class hive3TipYikes {

    //too bad dylan
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(7.5, 15, 270);
    private final Pose shootPreloads = poseFactory.of(12, 12, 220);
    private final Pose intakeFour = poseFactory.of(7, 8, 270);
    private final Pose shootFour = poseFactory.of(48, 120, 100);
    private final Pose point3Control1 = poseFactory.of(33.809, 44.5813, 0);
    private final Pose point3Control2 = poseFactory.of(10.1072, 115.5654, 0);
    private final Pose intakeFlower1 = poseFactory.of(48, 130, 100);

    private final Pose intakeFromBot = poseFactory.of(48, 130, 100);
    private final Pose point5Control1 = poseFactory.of(61.1945, 114.4685, 0);

    private final Pose shootIntaken = poseFactory.of(56, 16, 270);
    private final Pose point6Control1 = poseFactory.of(8, 61, 0);
    private final Pose alignFlower2 = poseFactory.of(18, 48, 180);
    private final Pose intakeFlower2 = poseFactory.of(10, 48, 180);
    private final Pose shootFlower2 = poseFactory.of(56, 16, 270);
    private final Pose park = poseFactory.of(10, 92, -76.4081);
    private final Pose point10Control1 = poseFactory.of(20.1169, 50.1557, 0);

    public Path shootingPreloads() {
        return Paths.line(startPose, shootPreloads).linear(startPose, shootPreloads);
    }

    public Path intakingFour() {
        return Paths.line(shootPreloads, intakeFour).linear(shootPreloads, intakeFour);
    }

    public Path shootingFour() {
        return Paths.curve(intakeFour, point3Control1, point3Control2, shootFour).linear(intakeFour, shootFour);
    }

    public Path intakingFlower1() {
        return Paths.line(shootFour, intakeFlower1).constant(shootFour);
    }

    public Path intakingFromBot() {
        return Paths.curve(intakeFlower1, point5Control1, intakeFromBot).linear(intakeFlower1, intakeFromBot);
    }

    public Path shootingIntaken() {
        return Paths.curve(intakeFromBot, point6Control1, shootIntaken).linear(intakeFromBot, shootIntaken);
    }

    public Path alingingFlower2() {
        return Paths.line(shootIntaken, alignFlower2).tangent();
    }

    public Path intakingFlower2() {
        return Paths.line(alignFlower2, intakeFlower2).tangent();
    }

    public Path shootingFlower2() {
        return Paths.line(intakeFlower2, shootFlower2).linear(intakeFlower2, shootFlower2);
    }

    public Path Finale() {
        return Paths.curve(shootFlower2, point10Control1, park).reverseTangent();
    }


}
*/