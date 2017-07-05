package ua.com.codeasylum.menulibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by User on 04.07.2017.
 */

public class AnimationsController {
    private static int DURATION = 300;
    private boolean isShow;
    private LinearLayout[] views;
    private LinearLayout menuRoot;


    AnimationsController(LinearLayout menuRoot, LinearLayout[] views){
        this.views = views;
        this.menuRoot = menuRoot;
    }

    // start block which responsible for work with animations
    private ObjectAnimator createOAFromShow(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -2000f, 0f);
        animation.addListener(AnimationListenerForItems(view));
        animation.setDuration(DURATION);
        return animation;
    }


    void showMenu() {
        if (!isShow) {
            isShow = true;
            showMenuAnimator().start();
        }
    }

    void hideMenu() {
        if (isShow) {
            isShow = false;
            hideMenuAnimator().start();
        }
    }

    private AnimatorSet showMenuAnimator() {
        AnimatorSet set = new AnimatorSet();

        set.playSequentially(
                createOAFromShow(menuRoot),
                createOAFromShow(views[3]),
                createOAFromShow(views[2]),
                createOAFromShow(views[1]),
                createOAFromShow(views[0])
        );
        set.addListener(AnimationListenerForSet());
        return set;
    }

    private AnimatorSet hideMenuAnimator() {

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(
                ObjectAnimator.ofFloat(menuRoot, View.TRANSLATION_Y, 0f, -2000f)
        );
        set.setDuration(DURATION);
        set.addListener(AnimationListenerForSet());
        return set;
    }

    private AnimatorListenerAdapter AnimationListenerForItems(final View view) {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        };
    }

    private AnimatorListenerAdapter AnimationListenerForSet() {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isShow) {
                    for(View view : views){
                        view.setClickable(true);
                    }
                } else {
                    for(View view : views){
                        view.setClickable(false);
                    }
                    hideItems();
                }
            }
        };
    }

    private void hideItems() {
        for (View view : views) {
            if (view.getId() == menuRoot.getId()) {
                view.setVisibility(View.GONE);
            } else if (view.getId() != menuRoot.getId() && view.getId() != views[4].getId()) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }


    // end block with animations

}
