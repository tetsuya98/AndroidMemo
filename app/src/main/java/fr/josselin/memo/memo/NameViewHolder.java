package fr.josselin.memo.memo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.widget.Toast.makeText;

/**
 * Created by jos_b on 22/02/2018.
 */

public class NameViewHolder extends RecyclerView.ViewHolder {

    private TextView tv;
    private TextView id;
    private CardView cv;
    private TextView content;
    private TextView contentfull;

    public NameViewHolder(final Context context, View itemView) {
        super(itemView);

        tv = itemView.findViewById(R.id.memo);
        cv = itemView.findViewById(R.id.cv);
        id = itemView.findViewById(R.id.id);
        content = itemView.findViewById(R.id.content);
        contentfull = itemView.findViewById(R.id.contentfull);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).intent(id.getText().toString(), tv.getText().toString(), contentfull.getText().toString());
            }
        });
}

    public void bind(Memo memo) {
        tv.setText(memo.getTitle());
        id.setText(memo.getId().toString());
        String txt = memo.getText();
        if (txt != null) {
            if (txt.length() > 50 ){
                content.setText(memo.getText().substring(0,50)+"...");
            } else {content.setText(memo.getText());}
            contentfull.setText(memo.getText());
        }else {content.setText(memo.getText());}


    }
}
