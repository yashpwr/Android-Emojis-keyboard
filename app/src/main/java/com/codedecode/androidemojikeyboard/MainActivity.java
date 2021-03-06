package com.codedecode.androidemojikeyboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    CheckBox mCheckBox;
    EmojiconEditText emojiconEditText;
    EmojiconTextView textView;
    ImageView emojiImageView;
    ImageView submitButton;
    View rootView;
    EmojIconActions emojIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.root_view);
        emojiImageView = findViewById(R.id.emoji_btn);
        submitButton = findViewById(R.id.submit_btn);
        mCheckBox = findViewById(R.id.use_system_default);
        emojiconEditText = findViewById(R.id.emojicon_edit_text);
        textView = findViewById(R.id.textView);

        emojIcon = new EmojIconActions(this, rootView, emojiconEditText, emojiImageView);

        //Changing the Emoji Keyboard colors to match your app theme
        //emojIcon = new EmojIconActions(this, rootView, emojiconEditText, emojiImageView, "#303F9F","#e8e8e8","#f4f4f4");

        emojIcon.ShowEmojIcon();

        //Change the default Toggle Icon
        //emojIcon.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.smileys);

        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e(TAG, "Keyboard is Opened!");
            }

            @Override
            public void onKeyboardClose() {
                Log.e(TAG, "Keyboard is Closed");
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emojIcon.setUseSystemEmoji(b);
                textView.setUseSystemDefault(b);

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = emojiconEditText.getText().toString();
                textView.setText(newText);
            }
        });
    }
}
