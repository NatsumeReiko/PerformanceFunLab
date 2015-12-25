package lab.fun.performance.javaheap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lab.fun.performance.R;
import lab.fun.performance.customview.TargetTextView;

public class TargetTextViewHeapObjectCountTest extends AppCompatActivity implements View.OnClickListener {
    protected TargetTextView user_input_text_true, user_input_text_false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_target_text_view_heap_object_count_test);
//        ButterKnife.bind(this);

        initializeView();

        if (savedInstanceState != null) {
            user_input_text_true.setText(savedInstanceState.getString(SaveInstance.test_is_user_input_text_true.name()));

        }
    }

    /**
     * initialize views
     */
    private void initializeView() {
//        findViewById(R.id.make_target_data).setOnClickListener(this);
        user_input_text_true = (TargetTextView) findViewById(R.id.test_is_user_input_text_true);
        user_input_text_false = (TargetTextView) findViewById(R.id.test_is_user_input_text_false);

    }

    //    @OnClick({R.id.make_target_data})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make_target_data: {
            }
            break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SaveInstance.test_is_user_input_text_true.name(), user_input_text_true.getText().toString());
    }


    protected enum SaveInstance {
        test_is_user_input_text_true,
    }
}
