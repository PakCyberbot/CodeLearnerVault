package io.hextree.attacksurface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import io.hextree.attacksurface.AppCompactActivity;
import io.hextree.attacksurface.LogHelper;
import io.hextree.attacksurface.SolvedPreferences;

/* loaded from: classes.dex */
public class Flag4Activity extends AppCompactActivity {
    public Flag4Activity() {
        this.name = "Flag 4 - State machine";
        this.flag = "2ftukoQ59QLkG42FGkCkdyK7+Jwi0uY7QfC2sPyofRcgvI+kzSIwqP0vMJ9fCbRn";
    }

    public enum State {
        INIT(0),
        PREPARE(1),
        BUILD(2),
        GET_FLAG(3),
        REVERT(4);

        private final int value;

        State(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static State fromInt(int i) {
            for (State state : values()) {
                if (state.getValue() == i) {
                    return state;
                }
            }
            return INIT;
        }
    }

    @Override // io.hextree.attacksurface.AppCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new LogHelper(this);
        stateMachine(getIntent());
    }

    private State getCurrentState() {
        return State.fromInt(SolvedPreferences.getInt(getPrefixKey("state")));
    }

    private void setCurrentState(State state) {
        SolvedPreferences.putInt(getPrefixKey("state"), state.getValue());
    }

    public void stateMachine(Intent intent) {
        String action = intent.getAction();
        int iOrdinal = getCurrentState().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    if (iOrdinal == 3) {
                        this.f.addTag(State.GET_FLAG);
                        setCurrentState(State.INIT);
                        success(this);
                        Log.i("Flag4StateMachine", "solved");
                        return;
                    }
                    if (iOrdinal == 4 && "INIT_ACTION".equals(action)) {
                        setCurrentState(State.INIT);
                        Toast.makeText(this, "Transitioned from REVERT to INIT", 0).show();
                        Log.i("Flag4StateMachine", "Transitioned from REVERT to INIT");
                        return;
                    }
                } else if ("GET_FLAG_ACTION".equals(action)) {
                    setCurrentState(State.GET_FLAG);
                    Toast.makeText(this, "Transitioned from BUILD to GET_FLAG", 0).show();
                    Log.i("Flag4StateMachine", "Transitioned from BUILD to GET_FLAG");
                    return;
                }
            } else if ("BUILD_ACTION".equals(action)) {
                setCurrentState(State.BUILD);
                Toast.makeText(this, "Transitioned from PREPARE to BUILD", 0).show();
                Log.i("Flag4StateMachine", "Transitioned from PREPARE to BUILD");
                return;
            }
        } else if ("PREPARE_ACTION".equals(action)) {
            setCurrentState(State.PREPARE);
            Toast.makeText(this, "Transitioned from INIT to PREPARE", 0).show();
            Log.i("Flag4StateMachine", "Transitioned from INIT to PREPARE");
            return;
        }
        Toast.makeText(this, "Unknown state. Transitioned to INIT", 0).show();
        Log.i("Flag4StateMachine", "Unknown state. Transitioned to INIT");
        setCurrentState(State.INIT);
    }
}