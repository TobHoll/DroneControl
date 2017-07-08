package com.example.tobiashollarek.dronecontrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.tobiashollarek.dronecontrol.Position.A;
import static com.example.tobiashollarek.dronecontrol.Position.B;
import static com.example.tobiashollarek.dronecontrol.Position.C;

public class MainActivity extends AppCompatActivity
    implements DronePositionCallback{

    @Bind(R.id.btn_positiona)
    Button buttonA;
    @Bind(R.id.btn_positionb)
    Button buttonB;
    @Bind(R.id.btn_positionc)
    Button buttonC;

    DroneAPI droneApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        droneApi = new DroneAPI(this);
        setButtonState(buttonA, ButtonState.DISABLED);
        setButtonState(buttonB, ButtonState.ACTIVE);
        setButtonState(buttonC, ButtonState.ACTIVE);
    }

    @OnClick(R.id.btn_positiona)
    public void onButtonAClicked() {
        setAllButtonsToWaiting();
        flyToPosition(A);
    }

    @OnClick(R.id.btn_positionb)
    public void onButtonBClicked() {
        setAllButtonsToWaiting();
        flyToPosition(B);
    }

    @OnClick(R.id.btn_positionc)
    public void onButtonCClicked() {
        setAllButtonsToWaiting();
        flyToPosition(C);
    }

    @Override
    public void onPositionReached(final Position position) {
        switch (position){
            case A:
                setButtonState(buttonA, ButtonState.DISABLED);
                setButtonState(buttonB, ButtonState.ACTIVE);
                setButtonState(buttonC, ButtonState.ACTIVE);
                break;
            case B:
                setButtonState(buttonA, ButtonState.ACTIVE);
                setButtonState(buttonB, ButtonState.DISABLED);
                setButtonState(buttonC, ButtonState.ACTIVE);
                break;
            case C:
                setButtonState(buttonA, ButtonState.ACTIVE);
                setButtonState(buttonB, ButtonState.ACTIVE);
                setButtonState(buttonC, ButtonState.DISABLED);
                break;
        }
    }

    private void flyToPosition(Position position) {
        droneApi.flyTo(position);
    }

    private void setAllButtonsToWaiting() {
        setButtonState(buttonA, ButtonState.WAITING);
        setButtonState(buttonB, ButtonState.WAITING);
        setButtonState(buttonC, ButtonState.WAITING);
    }

    private void setButtonState(Button button, ButtonState state) {
        switch (state) {
            case ACTIVE:
                button.setClickable(true);
                button.setBackgroundColor(Color.GREEN);
                break;
            case WAITING:
                button.setClickable(false);
                button.setBackgroundColor(Color.RED);
                break;
            case DISABLED:
                button.setClickable(false);
                button.setBackgroundColor(Color.GRAY);
        }
    }
}
