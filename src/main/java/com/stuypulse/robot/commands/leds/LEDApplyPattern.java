package com.stuypulse.robot.commands.leds;

import java.util.function.Supplier;

import com.stuypulse.robot.subsystems.leds.LEDController;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.Command;

public class LEDApplyPattern extends Command{
    protected final LEDController leds;
    protected final Supplier<LEDPattern> pattern;

    public LEDApplyPattern(Supplier<LEDPattern> pattern) {
        leds = LEDController.getInstance();
        this.pattern = pattern;

        addRequirements(leds); 
    }

    public LEDApplyPattern(LEDPattern pattern) {
        this(() -> pattern);
    }

    @Override
    public void execute(){
        leds.applyPattern(pattern.get());
    }
}
