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
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(54, 9, 270);
    private final Pose alignFour = poseFactory.of(8, 19.5, 270);
    private final Pose intakeFour = poseFactory.of(8, 10, 270);
    private final Pose alignToShoot = poseFactory.of(36, 110, -105.6422);
    private final Pose shootFour = poseFactory.of(48, 123, 90);
    private final Pose intakeFlower1 = poseFactory.of(48, 130, 90);
    private final Pose moveBackFlower1 = poseFactory.of(30, 130, 90);
    private final Pose alignFlower2 = poseFactory.of(30, 48, 90);
    private final Pose intakeFlower2 = poseFactory.of(9, 48, 180);
    private final Pose moveBackFlower2 = poseFactory.of(18, 48, 180);
    private final Pose shootFlower2 = poseFactory.of(45, 130, -60);
    private final Pose park = poseFactory.of(19, 98, -129.0939);

    public Path alignToFour() {
        return Paths.line(start, alignFour).linear(start, alignFour);
    }

    public Path intakeFour() {
        return Paths.line(alignFour, intakeFour).constant(alignFour);
    }

    public Path alignToShoot() {
        return Paths.line(intakeFour, alignToShoot).reverseTangent();
    }

    public Path shootFour() {
        return Paths.line(alignToShoot, shootFour).constant(shootFour);
    }

    public Path intakeBackFlower1() {
        return Paths.line(shootFour, intakeFlower1).tangent();
    }

    public Path moveBackFlower1() {
        return Paths.line(intakeFlower1, moveBackFlower1).constant(moveBackFlower1);
    }

    public Path alignFlower2() {
        return Paths.line(moveBackFlower1, alignFlower2).reverseTangent();
    }

    public Path intakeFlower2() {
        return Paths.line(alignFlower2, intakeFlower2).tangent();
    }

    public Path moveBackFlower2() {
        return Paths.line(intakeFlower2, moveBackFlower2).reverseTangent();
    }

    public Path shootFlower2() {
        return Paths.line(moveBackFlower2, shootFlower2).linear(moveBackFlower2, shootFlower2);
    }

    public Path park() {
        return Paths.line(shootFlower2, park).tangent();
    }
}

