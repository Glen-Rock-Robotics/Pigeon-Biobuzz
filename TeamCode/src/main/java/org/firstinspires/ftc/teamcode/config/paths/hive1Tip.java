package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class hive1Tip {
    //private final Follower follower;

    public static final PoseFactory poseFactory = PoseFactory.degrees();
    ///HEADING IS NOW IN DEGREES

    public static final Pose startPose = poseFactory.of(54.5, 9, 90);
    public static final Pose alignPush = poseFactory.of(7.5, 64, 270);
    public static final Pose startPush = poseFactory.of(7.5, 95, 270);
    public static final Pose leave = poseFactory.of(12, 95, 270);

    public static Path alignToPush() {
        return Paths.line(startPose, alignPush).constant(alignPush);
    }

    public static Path pushing() {
        return Paths.line(alignPush, startPush).reverseTangent();
    }

    public static Path leaving() {
        return Paths.line(startPush, leave).constant(leave);
    }


}
