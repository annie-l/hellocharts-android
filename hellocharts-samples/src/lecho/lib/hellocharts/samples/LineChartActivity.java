package lecho.lib.hellocharts.samples;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A fragment containing a line chart.
     */
    public static class PlaceholderFragment extends Fragment {

        private LineChartView chart;
        private LineChartData data;
        private int numberOfLines = 1;
        private int maxNumberOfLines = 4;
        private int numberOfPoints = 12;

        float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

        private boolean hasAxes = true;
        private boolean hasAxesNames = true;
        private boolean hasLines = true;
        private boolean hasPoints = true;
        private ValueShape shape = ValueShape.CIRCLE;
        private boolean isFilled = false;
        private boolean hasLabels = false;
        private boolean isCubic = false;
        private boolean hasLabelForSelected = false;
        private boolean pointsHaveDifferentColor;
        private boolean hasGradientToTransparent = false;
        private boolean hasGradient = false;


        static double[][] rgbVals = {{0.49412352941176468, 0.0, 0.56076470588235294},
                {0.49934705882352942, 0.0, 0.56599607843137256},
                {0.5045705882352941, 0.0, 0.57122745098039218},
                {0.50979411764705884, 0.0, 0.5764588235294118},
                {0.51501764705882347, 0.0, 0.58169019607843131},
                {0.52024117647058821, 0.0, 0.58692156862745093},
                {0.52546470588235294, 0.0, 0.59215294117647055},
                {0.53068823529411768, 0.0, 0.59738431372549017},
                {0.5123862745098039, 0.0, 0.60261568627450979},
                {0.47055882352941175, 0.0, 0.60784705882352941},
                {0.42873137254901961, 0.0, 0.61307843137254903},
                {0.38690392156862741, 0.0, 0.61830980392156865},
                {0.34507647058823532, 0.0, 0.62354117647058815},
                {0.30324901960784312, 0.0, 0.62877254901960777},
                {0.26142156862745097, 0.0, 0.63400392156862739},
                {0.21959411764705911, 0.0, 0.63923529411764701},
                {0.17776666666666668, 0.0, 0.64446666666666663},
                {0.13593921568627448, 0.0, 0.64969803921568625},
                {0.094111764705882395, 0.0, 0.65492941176470587},
                {0.052284313725490472, 0.0, 0.66016078431372538},
                {0.01045686274509805, 0.0, 0.665392156862745},
                {0.0, 0.0, 0.67846470588235286},
                {0.0, 0.0, 0.69415098039215684},
                {0.0, 0.0, 0.7098372549019607},
                {0.0, 0.0, 0.72552352941176468},
                {0.0, 0.0, 0.74120980392156866},
                {0.0, 0.0, 0.75689607843137252},
                {0.0, 0.0, 0.77258235294117639},
                {0.0, 0.0, 0.78826862745098036},
                {0.0, 0.0, 0.80395490196078434},
                {0.0, 0.0, 0.81964117647058821},
                {0.0, 0.0, 0.83532745098039207},
                {0.0, 0.0, 0.85101372549019605},
                {0.0, 0.0, 0.86670000000000003},
                {0.0, 0.036603921568627448, 0.86670000000000003},
                {0.0, 0.073207843137254647, 0.86670000000000003},
                {0.0, 0.10981176470588236, 0.86670000000000003},
                {0.0, 0.14641568627450979, 0.86670000000000003},
                {0.0, 0.18301960784313726, 0.86670000000000003},
                {0.0, 0.21962352941176444, 0.86670000000000003},
                {0.0, 0.25622745098039218, 0.86670000000000003},
                {0.0, 0.29283137254901959, 0.86670000000000003},
                {0.0, 0.3294352941176471, 0.86670000000000003},
                {0.0, 0.36603921568627423, 0.86670000000000003},
                {0.0, 0.40264313725490197, 0.86670000000000003},
                {0.0, 0.43924705882352943, 0.86670000000000003},
                {0.0, 0.46931372549019607, 0.86670000000000003},
                {0.0, 0.47976862745098037, 0.86670000000000003},
                {0.0, 0.49022352941176456, 0.86670000000000003},
                {0.0, 0.50067843137254897, 0.86670000000000003},
                {0.0, 0.51113333333333333, 0.86670000000000003},
                {0.0, 0.52158823529411769, 0.86670000000000003},
                {0.0, 0.53204313725490193, 0.86670000000000003},
                {0.0, 0.54249803921568629, 0.86670000000000003},
                {0.0, 0.55295294117647054, 0.86670000000000003},
                {0.0, 0.56340784313725489, 0.86670000000000003},
                {0.0, 0.57386274509803903, 0.86670000000000003},
                {0.0, 0.5843176470588235, 0.86670000000000003},
                {0.0, 0.59477254901960785, 0.86670000000000003},
                {0.0, 0.60261568627450979, 0.85885686274509809},
                {0.0, 0.60784705882352941, 0.84317058823529412},
                {0.0, 0.61307843137254903, 0.82748431372549025},
                {0.0, 0.61830980392156865, 0.81179803921568627},
                {0.0, 0.62354117647058815, 0.79611176470588241},
                {0.0, 0.62877254901960777, 0.78042549019607865},
                {0.0, 0.63400392156862739, 0.76473921568627445},
                {0.0, 0.63923529411764701, 0.74905294117647059},
                {0.0, 0.64446666666666663, 0.73336666666666672},
                {0.0, 0.64969803921568625, 0.71768039215686275},
                {0.0, 0.65492941176470587, 0.70199411764705877},
                {0.0, 0.66016078431372549, 0.6863078431372549},
                {0.0, 0.665392156862745, 0.67062156862745093},
                {0.0, 0.66669999999999996, 0.65885294117647075},
                {0.0, 0.66669999999999996, 0.64839019607843129},
                {0.0, 0.66669999999999996, 0.63792745098039216},
                {0.0, 0.66669999999999996, 0.62746470588235292},
                {0.0, 0.66669999999999996, 0.61700196078431369},
                {0.0, 0.66669999999999996, 0.60653921568627445},
                {0.0, 0.66669999999999996, 0.59607647058823532},
                {0.0, 0.66669999999999996, 0.58561372549019608},
                {0.0, 0.66669999999999996, 0.57515098039215695},
                {0.0, 0.66669999999999996, 0.5646882352941176},
                {0.0, 0.66669999999999996, 0.55422549019607836},
                {0.0, 0.66669999999999996, 0.54376274509803924},
                {0.0, 0.66669999999999996, 0.5333},
                {0.0, 0.66146862745098034, 0.49147254901960785},
                {0.0, 0.65623725490196072, 0.44964509803921571},
                {0.0, 0.6510058823529411, 0.40781764705882351},
                {0.0, 0.64577450980392159, 0.36599019607843197},
                {0.0, 0.64054313725490197, 0.32416274509803922},
                {0.0, 0.63531176470588235, 0.28233529411764707},
                {0.0, 0.63008039215686273, 0.24050784313725487},
                {0.0, 0.62484901960784311, 0.19868039215686273},
                {0.0, 0.6196176470588235, 0.15685294117647058},
                {0.0, 0.61438627450980388, 0.11502549019607844},
                {0.0, 0.60915490196078426, 0.073198039215686239},
                {0.0, 0.60392352941176475, 0.031370588235294705},
                {0.0, 0.60261372549019609, 0.0},
                {0.0, 0.61306862745098034, 0.0},
                {0.0, 0.6235235294117647, 0.0},
                {0.0, 0.63397843137254895, 0.0},
                {0.0, 0.6444333333333333, 0.0},
                {0.0, 0.65488823529411766, 0.0},
                {0.0, 0.66534313725490191, 0.0},
                {0.0, 0.67579803921568604, 0.0},
                {0.0, 0.68625294117647051, 0.0},
                {0.0, 0.69670784313725487, 0.0},
                {0.0, 0.70716274509803922, 0.0},
                {0.0, 0.71761764705882347, 0.0},
                {0.0, 0.72807254901960783, 0.0},
                {0.0, 0.73853137254901957, 0.0},
                {0.0, 0.74899411764705881, 0.0},
                {0.0, 0.75945686274509805, 0.0},
                {0.0, 0.76991960784313718, 0.0},
                {0.0, 0.78038235294117619, 0.0},
                {0.0, 0.79084509803921565, 0.0},
                {0.0, 0.80130784313725489, 0.0},
                {0.0, 0.81177058823529413, 0.0},
                {0.0, 0.82223333333333337, 0.0},
                {0.0, 0.83269607843137261, 0.0},
                {0.0, 0.84315882352941174, 0.0},
                {0.0, 0.85362156862745098, 0.0},
                {0.0, 0.86408431372549022, 0.0},
                {0.0, 0.87454117647058827, 0.0},
                {0.0, 0.88499607843137262, 0.0},
                {0.0, 0.89545098039215687, 0.0},
                {0.0, 0.90590588235294123, 0.0},
                {0.0, 0.91636078431372547, 0.0},
                {0.0, 0.92681568627450983, 0.0},
                {0.0, 0.93727058823529408, 0.0},
                {0.0, 0.9477254901960781, 0.0},
                {0.0, 0.95818039215686279, 0.0},
                {0.0, 0.96863529411764704, 0.0},
                {0.0, 0.9790901960784314, 0.0},
                {0.0, 0.98954509803921564, 0.0},
                {0.0, 1.0, 0.0},
                {0.057513725490196073, 1.0, 0.0},
                {0.11502745098039215, 1.0, 0.0},
                {0.17254117647058823, 1.0, 0.0},
                {0.23005490196078429, 1.0, 0.0},
                {0.28756862745098039, 1.0, 0.0},
                {0.34508235294117645, 1.0, 0.0},
                {0.40259607843137257, 1.0, 0.0},
                {0.46010980392156858, 1.0, 0.0},
                {0.5176235294117647, 1.0, 0.0},
                {0.57513725490196077, 1.0, 0.0},
                {0.63265098039215517, 1.0, 0.0},
                {0.6901647058823529, 1.0, 0.0},
                {0.73722156862745092, 0.99869215686274515, 0.0},
                {0.7529078431372549, 0.99346078431372553, 0.0},
                {0.76859411764705876, 0.98822941176470591, 0.0},
                {0.78428039215686274, 0.98299803921568629, 0.0},
                {0.7999666666666666, 0.97776666666666667, 0.0},
                {0.81565294117647058, 0.97253529411764705, 0.0},
                {0.83133921568627445, 0.96730392156862743, 0.0},
                {0.84702549019607842, 0.96207254901960781, 0.0},
                {0.8627117647058824, 0.9568411764705882, 0.0},
                {0.87839803921568627, 0.95160980392156869, 0.0},
                {0.89408431372549024, 0.94637843137254907, 0.0},
                {0.90977058823529411, 0.94114705882352945, 0.0},
                {0.92545686274509809, 0.93591568627450983, 0.0},
                {0.93591568627450983, 0.9280725490196079, 0.0},
                {0.94114705882352923, 0.91761764705882387, 0.0},
                {0.94637843137254907, 0.90716274509803929, 0.0},
                {0.95160980392156869, 0.89670784313725493, 0.0},
                {0.9568411764705882, 0.88625294117647058, 0.0},
                {0.96207254901960781, 0.87579803921568633, 0.0},
                {0.96730392156862743, 0.86534313725490197, 0.0},
                {0.97253529411764705, 0.85488823529411773, 0.0},
                {0.97776666666666667, 0.84443333333333337, 0.0},
                {0.98299803921568629, 0.83397843137254901, 0.0},
                {0.98822941176470591, 0.82352352941176477, 0.0},
                {0.99346078431372553, 0.81306862745098041, 0.0},
                {0.99869215686274515, 0.80261372549019616, 0.0},
                {1.0, 0.78823529411764715, 0.0},
                {1.0, 0.77254901960784317, 0.0},
                {1.0, 0.75686274509803919, 0.0},
                {1.0, 0.74117647058823533, 0.0},
                {1.0, 0.72549019607843179, 0.0},
                {1.0, 0.70980392156862748, 0.0},
                {1.0, 0.69411764705882351, 0.0},
                {1.0, 0.67843137254901964, 0.0},
                {1.0, 0.66274509803921566, 0.0},
                {1.0, 0.6470588235294118, 0.0},
                {1.0, 0.63137254901960782, 0.0},
                {1.0, 0.61568627450980395, 0.0},
                {1.0, 0.59999999999999998, 0.0},
                {1.0, 0.55294117647058827, 0.0},
                {1.0, 0.50588235294117645, 0.0},
                {1.0, 0.45882352941176469, 0.0},
                {1.0, 0.41176470588235292, 0.0},
                {1.0, 0.36470588235294116, 0.0},
                {1.0, 0.31764705882352939, 0.0},
                {1.0, 0.27058823529411763, 0.0},
                {1.0, 0.2235294117647072, 0.0},
                {1.0, 0.1764705882352941, 0.0},
                {1.0, 0.12941176470588234, 0.0},
                {1.0, 0.082352941176470518, 0.0},
                {1.0, 0.035294117647058809, 0.0},
                {0.99738627450980388, 0.0, 0.0},
                {0.98693137254901964, 0.0, 0.0},
                {0.97647647058823528, 0.0, 0.0},
                {0.96602156862745103, 0.0, 0.0},
                {0.95556666666666668, 0.0, 0.0},
                {0.94511176470588232, 0.0, 0.0},
                {0.93465686274509807, 0.0, 0.0},
                {0.92420196078431371, 0.0, 0.0},
                {0.91374705882352947, 0.0, 0.0},
                {0.90329215686274511, 0.0, 0.0},
                {0.89283725490196075, 0.0, 0.0},
                {0.88238235294117684, 0.0, 0.0},
                {0.87192745098039215, 0.0, 0.0},
                {0.86408431372549022, 0.0, 0.0},
                {0.8588529411764706, 0.0, 0.0},
                {0.85362156862745098, 0.0, 0.0},
                {0.84839019607843136, 0.0, 0.0},
                {0.84315882352941185, 0.0, 0.0},
                {0.83792745098039223, 0.0, 0.0},
                {0.83269607843137261, 0.0, 0.0},
                {0.82746470588235299, 0.0, 0.0},
                {0.82223333333333337, 0.0, 0.0},
                {0.81700196078431375, 0.0, 0.0},
                {0.81177058823529413, 0.0, 0.0},
                {0.80653921568627451, 0.0, 0.0},
                {0.80130784313725489, 0.0, 0.0},
                {0.80000000000000004, 0.047058823529411764, 0.047058823529411764},
                {0.80000000000000004, 0.10980392156862567, 0.10980392156862567},
                {0.80000000000000004, 0.17254901960784316, 0.17254901960784316},
                {0.80000000000000004, 0.23529411764705885, 0.23529411764705885},
                {0.80000000000000004, 0.29803921568627451, 0.29803921568627451},
                {0.80000000000000004, 0.36078431372549025, 0.36078431372549025},
                {0.80000000000000004, 0.42352941176470593, 0.42352941176470593},
                {0.80000000000000004, 0.48627450980392156, 0.48627450980392156},
                {0.80000000000000004, 0.5490196078431373, 0.5490196078431373},
                {0.80000000000000004, 0.61176470588235299, 0.61176470588235299},
                {0.80000000000000004, 0.67450980392156867, 0.67450980392156867},
                {0.80000000000000004, 0.73725490196078436, 0.73725490196078436},
                {0.80000000000000004, 0.80000000000000004, 0.80000000000000004}};

        static int[] RAINBOW_COLORS;
        static {
            RAINBOW_COLORS = new int[rgbVals.length];
            for (int i = 0; i < rgbVals.length; i++) {
                RAINBOW_COLORS[i] = Color.rgb((int) (rgbVals[rgbVals.length - 1 - i][0] * 255),
                        (int) (rgbVals[rgbVals.length - 1 - i][1] * 255),
                        (int) (rgbVals[rgbVals.length - 1 - i][2] * 255));
            }
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

            chart = (LineChartView) rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());

            // Generate some random values.
            generateValues();

            generateData();

            // Disable viewport recalculations, see toggleCubic() method for more info.
            chart.setViewportCalculationEnabled(false);

            resetViewport();

            return rootView;
        }

        // MENU
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.line_chart, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_reset) {
                reset();
                generateData();
                return true;
            }
            if (id == R.id.action_add_line) {
                addLineToData();
                return true;
            }
            if (id == R.id.action_toggle_lines) {
                toggleLines();
                return true;
            }
            if (id == R.id.action_toggle_points) {
                togglePoints();
                return true;
            }
            if (id == R.id.action_toggle_gradient_transparent) {
                toggleGradientTransparent();
                return true;
            }
            if (id == R.id.action_toggle_gradient) {
                toggleGradient();
                return true;
            }
            if (id == R.id.action_toggle_cubic) {
                toggleCubic();
                return true;
            }
            if (id == R.id.action_toggle_area) {
                toggleFilled();
                return true;
            }
            if (id == R.id.action_point_color) {
                togglePointColor();
                return true;
            }
            if (id == R.id.action_shape_circles) {
                setCircles();
                return true;
            }
            if (id == R.id.action_shape_square) {
                setSquares();
                return true;
            }
            if (id == R.id.action_shape_diamond) {
                setDiamonds();
                return true;
            }
            if (id == R.id.action_toggle_labels) {
                toggleLabels();
                return true;
            }
            if (id == R.id.action_toggle_axes) {
                toggleAxes();
                return true;
            }
            if (id == R.id.action_toggle_axes_names) {
                toggleAxesNames();
                return true;
            }
            if (id == R.id.action_animate) {
                prepareDataAnimation();
                chart.startDataAnimation();
                return true;
            }
            if (id == R.id.action_toggle_selection_mode) {
                toggleLabelForSelected();

                Toast.makeText(getActivity(),
                        "Selection mode set to " + chart.isValueSelectionEnabled() + " select any point.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_toggle_touch_zoom) {
                chart.setZoomEnabled(!chart.isZoomEnabled());
                Toast.makeText(getActivity(), "IsZoomEnabled " + chart.isZoomEnabled(), Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_zoom_both) {
                chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
                return true;
            }
            if (id == R.id.action_zoom_horizontal) {
                chart.setZoomType(ZoomType.HORIZONTAL);
                return true;
            }
            if (id == R.id.action_zoom_vertical) {
                chart.setZoomType(ZoomType.VERTICAL);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        private void generateValues() {
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfPoints; ++j) {
                    randomNumbersTab[i][j] = (float) Math.random() * 100f;
                }
            }
        }

        private void reset() {
            numberOfLines = 1;

            hasAxes = true;
            hasAxesNames = true;
            hasLines = true;
            hasPoints = true;
            shape = ValueShape.CIRCLE;
            isFilled = false;
            hasLabels = false;
            isCubic = false;
            hasLabelForSelected = false;
            pointsHaveDifferentColor = false;

            chart.setValueSelectionEnabled(hasLabelForSelected);
            resetViewport();
        }

        private void resetViewport() {
            // Reset viewport height range to (0,100)
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = 0;
            v.top = 100;
            v.left = 0;
            v.right = numberOfPoints - 1;
            chart.setMaximumViewport(v);
            chart.setCurrentViewport(v);
        }

        private void generateData() {

            List<Line> lines = new ArrayList<Line>();
            for (int i = 0; i < numberOfLines; ++i) {

                List<PointValue> values = new ArrayList<PointValue>();
                for (int j = 0; j < numberOfPoints; ++j) {
                    values.add(new PointValue(j, randomNumbersTab[i][j]));
                }

                Line line = new Line(values);
                line.setColor(ChartUtils.COLORS[i]);
                line.setShape(shape);
                line.setCubic(isCubic);
                line.setFilled(isFilled);
                line.setHasLabels(hasLabels);
                line.setHasLabelsOnlyForSelected(hasLabelForSelected);
                line.setHasLines(hasLines);
                line.setHasPoints(hasPoints);
                line.setHasGradientToTransparent(hasGradientToTransparent);
                line.setHasGradient(hasGradient);
                if (hasGradient) {
                    line.setGradientRange(RAINBOW_COLORS);
                }
                if (pointsHaveDifferentColor){
                    line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
                }
                lines.add(line);
            }

            data = new LineChartData(lines);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            data.setBaseValue(Float.NEGATIVE_INFINITY);
            chart.setLineChartData(data);

        }

        /**
         * Adds lines to data, after that data should be set again with
         * {@link LineChartView#setLineChartData(LineChartData)}. Last 4th line has non-monotonically x values.
         */
        private void addLineToData() {
            if (data.getLines().size() >= maxNumberOfLines) {
                Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                ++numberOfLines;
            }

            generateData();
        }

        private void toggleLines() {
            hasLines = !hasLines;

            generateData();
        }

        private void togglePoints() {
            hasPoints = !hasPoints;

            generateData();
        }

        private void toggleGradientTransparent() {
            hasGradientToTransparent = !hasGradientToTransparent;

            generateData();
        }

        private void toggleGradient() {
            hasGradient = !hasGradient;

            generateData();
        }

        private void toggleCubic() {
            isCubic = !isCubic;

            generateData();

            if (isCubic) {
                // It is good idea to manually set a little higher max viewport for cubic lines because sometimes line
                // go above or below max/min. To do that use Viewport.inest() method and pass negative value as dy
                // parameter or just set top and bottom values manually.
                // In this example I know that Y values are within (0,100) range so I set viewport height range manually
                // to (-5, 105).
                // To make this works during animations you should use Chart.setViewportCalculationEnabled(false) before
                // modifying viewport.
                // Remember to set viewport after you call setLineChartData().
                final Viewport v = new Viewport(chart.getMaximumViewport());
                v.bottom = -5;
                v.top = 105;
                // You have to set max and current viewports separately.
                chart.setMaximumViewport(v);
                // I changing current viewport with animation in this case.
                chart.setCurrentViewportWithAnimation(v);
            } else {
                // If not cubic restore viewport to (0,100) range.
                final Viewport v = new Viewport(chart.getMaximumViewport());
                v.bottom = 0;
                v.top = 100;

                // You have to set max and current viewports separately.
                // In this case, if I want animation I have to set current viewport first and use animation listener.
                // Max viewport will be set in onAnimationFinished method.
                chart.setViewportAnimationListener(new ChartAnimationListener() {

                    @Override
                    public void onAnimationStarted() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationFinished() {
                        // Set max viewpirt and remove listener.
                        chart.setMaximumViewport(v);
                        chart.setViewportAnimationListener(null);

                    }
                });
                // Set current viewpirt with animation;
                chart.setCurrentViewportWithAnimation(v);
            }

        }

        private void toggleFilled() {
            isFilled = !isFilled;

            generateData();
        }

        private void togglePointColor() {
            pointsHaveDifferentColor = !pointsHaveDifferentColor;

            generateData();
        }

        private void setCircles() {
            shape = ValueShape.CIRCLE;

            generateData();
        }

        private void setSquares() {
            shape = ValueShape.SQUARE;

            generateData();
        }

        private void setDiamonds() {
            shape = ValueShape.DIAMOND;

            generateData();
        }

        private void toggleLabels() {
            hasLabels = !hasLabels;

            if (hasLabels) {
                hasLabelForSelected = false;
                chart.setValueSelectionEnabled(hasLabelForSelected);
            }

            generateData();
        }

        private void toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected;

            chart.setValueSelectionEnabled(hasLabelForSelected);

            if (hasLabelForSelected) {
                hasLabels = false;
            }

            generateData();
        }

        private void toggleAxes() {
            hasAxes = !hasAxes;

            generateData();
        }

        private void toggleAxesNames() {
            hasAxesNames = !hasAxesNames;

            generateData();
        }

        /**
         * To animate values you have to change targets values and then call {@link Chart#startDataAnimation()}
         * method(don't confuse with View.animate()). If you operate on data that was set before you don't have to call
         * {@link LineChartView#setLineChartData(LineChartData)} again.
         */
        private void prepareDataAnimation() {
            for (Line line : data.getLines()) {
                for (PointValue value : line.getValues()) {
                    // Here I modify target only for Y values but it is OK to modify X targets as well.
                    value.setTarget(value.getX(), (float) Math.random() * 100);
                }
            }
        }

        private class ValueTouchListener implements LineChartOnValueSelectListener {

            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {
                // TODO Auto-generated method stub

            }

        }
    }
}
