package org.firstinspires.ftc.teamcode.config.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.config.robot.Robot;

public class hive2Tip {
    //private final Follower follower;

    public static final PoseFactory poseFactory = PoseFactory.degrees();
    ///HEADING IS NOW IN DEGREES

    public static final Pose startPose = poseFactory.of(24, 24, 0);
    public static final Pose scorePose = poseFactory.of(48, 48, 90);
    public static final Pose parkPose = poseFactory.of(72, 48, 90);



    public static Path startToScore() {
        return line(startPose, scorePose).linear(startPose, scorePose);
    }
    public static Path park(){
        return line(scorePose, parkPose).linear(scorePose, parkPose);
    }


}
