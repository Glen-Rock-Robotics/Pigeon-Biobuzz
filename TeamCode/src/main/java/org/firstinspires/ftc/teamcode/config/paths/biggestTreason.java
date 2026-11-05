package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.config.pedro.Constants;
import org.firstinspires.ftc.teamcode.config.robot.Robot;


public class biggestTreason {
    public static final PoseFactory poseFactory = PoseFactory.degrees();

    public static final Pose start = poseFactory.of(54, 9, 270);
    public static final Pose alignFour = poseFactory.of(8, 19.5, 270);
    public static final Pose intakeFour = poseFactory.of(8, 10, 270);
    public static final Pose alignToShoot = poseFactory.of(36, 110, -105.6422);
    public static final Pose shootFour = poseFactory.of(48, 123, 90);
    public static final Pose intakeFlower1 = poseFactory.of(48, 130, 90);
    public static final Pose moveBackFlower1 = poseFactory.of(30, 130, 90);
    public static final Pose alignFlower2 = poseFactory.of(30, 48, 90);
    public static final Pose intakeFlower2 = poseFactory.of(9, 48, 180);
    public static final Pose moveBackFlower2 = poseFactory.of(18, 48, 180);
    public static final Pose shootFlower2 = poseFactory.of(45, 130, -60);
    public static final Pose park = poseFactory.of(19, 98, -129.0939);

    public static Path alignToFour() {
        return Paths.line(start, alignFour).linear(start, alignFour);
    }

    public static Path intakeFour() {
        return Paths.line(alignFour, intakeFour).constant(alignFour);
    }

    public static Path alignToShoot() {
        return Paths.line(intakeFour, alignToShoot).reverseTangent();
    }

    public static Path shootFour() {
        return Paths.line(alignToShoot, shootFour).constant(shootFour);
    }

    public static Path intakeBackFlower1() {
        return Paths.line(shootFour, intakeFlower1).tangent();
    }

    public static Path moveBackFlower1() {
        return Paths.line(intakeFlower1, moveBackFlower1).constant(moveBackFlower1);
    }

    public static Path alignFlower2() {
        return Paths.line(moveBackFlower1, alignFlower2).reverseTangent();
    }

    public static Path intakeFlower2() {
        return Paths.line(alignFlower2, intakeFlower2).tangent();
    }

    public static Path moveBackFlower2() {
        return Paths.line(intakeFlower2, moveBackFlower2).reverseTangent();
    }

    public static Path shootFlower2() {
        return Paths.line(moveBackFlower2, shootFlower2).linear(moveBackFlower2, shootFlower2);
    }

    public static Path park() {
        return Paths.line(shootFlower2, park).tangent();
    }
}

