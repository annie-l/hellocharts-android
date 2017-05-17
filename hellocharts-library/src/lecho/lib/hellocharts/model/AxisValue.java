package lecho.lib.hellocharts.model;

import java.util.Arrays;
import lecho.lib.hellocharts.view.Chart;

/**
 * Single axis value, use it to manually set axis labels position. You can use label attribute to display text instead
 * of number but value formatter implementation have to handle it.
 */
public class AxisValue {
    private float value;
    private float origin;
    private float diff;
    private char[] label;

    public AxisValue(float value) {
        setValue(value);
    }

    @Deprecated
    public AxisValue(float value, char[] label) {
        this.value = value;
        this.label = label;
    }

    public AxisValue(AxisValue axisValue) {
        this.value = axisValue.value;
        this.label = axisValue.label;
    }

    public void update(float scale) {
        value = origin + diff * scale;
    }

    public void finish() {
        setValue(origin + diff);
    }

    public float getValue() {
        return value;
    }

    public AxisValue setValue(float value) {
        this.value = value;
        this.origin = value;
        this.diff = 0;
        return this;
    }

    /**
     * Set target value that should be reached when data animation finish then call {@link Chart#startDataAnimation()}
     */
    public AxisValue setTarget(float target) {
        setValue(value);
        this.diff = target - origin;
        return this;
    }

    @Deprecated
    public char[] getLabel() {
        return label;
    }

    /**
     * Set custom label for this axis value.
     *
     * @param label
     */
    public AxisValue setLabel(String label) {
        this.label = label.toCharArray();
        return this;
    }

    public char[] getLabelAsChars() {
        return label;
    }

    /**
     * Set custom label for this axis value.
     *
     * @param label
     */
    @Deprecated
    public AxisValue setLabel(char[] label) {
        this.label = label;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AxisValue axisValue = (AxisValue) o;

        return !(Float.compare(axisValue.value, value) != 0 ||
            Float.compare(axisValue.diff, diff) != 0 ||
            Float.compare(axisValue.origin, origin) != 0 ||
            !Arrays.equals(label, axisValue.label));
    }

    @Override
    public int hashCode() {
        int result = (value != +0.0f ? Float.floatToIntBits(value) : 0);
        result = 31 * result + (origin != +0.0f ? Float.floatToIntBits(origin) : 0);
        result = 31 * result + (diff != +0.0f ? Float.floatToIntBits(diff) : 0);
        result = 31 * result + (label != null ? Arrays.hashCode(label) : 0);
        return result;
    }
}