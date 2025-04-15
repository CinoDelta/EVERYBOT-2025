package com.stuypulse.robot.subsystems.pivot;

import com.stuypulse.robot.constants.Settings;
import com.stuypulse.stuylib.math.SLMath;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public abstract class Pivot extends SubsystemBase {
    private static final Pivot instance;

    static { 
        instance = new PivotImpl();
    }

    public static Pivot getInstance() {
        return instance;
    }

    protected PivotState pivotState;
    
    protected Pivot() {
        this.pivotState = PivotState.STOW_CORAL;
    }
    
    public enum PivotState {
        DEFAULT(Settings.Pivot.DEFAULT_ANGLE),
        STOW_CORAL(Settings.Pivot.CORAL_STOW_ANGLE),
        INTAKE_ALGAE(Settings.Pivot.ALGAE_INTAKE_ANGLE),
        STOW_ALGAE(Settings.Pivot.ALGAE_HOLDING_ANGLE);

        Rotation2d targetAngle;

        private PivotState(Rotation2d targetAngle) {
            this.targetAngle = 
                Rotation2d.fromDegrees(SLMath.clamp(
                        targetAngle.getDegrees(), 
                        Settings.Pivot.DEFAULT_ANGLE.getDegrees(), 
                        Settings.Pivot.MAX_ANGLE.getDegrees()));
        }

        public Rotation2d getTargetAngle() {
            return this.targetAngle;
        }
    }

    public abstract void setPivotState(PivotState pivotState);

    public abstract PivotState getPivotState();

    public abstract void setRollerMotor(double speed);

    public abstract void setPivotMotor(double speed);
    public abstract void ResetPivotEncoder();

    @Override
    public void periodic() {
        SmartDashboard.putString("Pivot/Pivot State", pivotState.toString());
        SmartDashboard.putNumber("Pivot/Target Angle", this.pivotState.getTargetAngle().getDegrees());
    }

    public abstract SysIdRoutine getSysIdRoutine();
}
