package ua.com.codeasylum.menulibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by User on 01.07.2017.
 */

public class MenuLibraryClass extends RelativeLayout {

    private enum MenuCommand{
        OPEN,CLOSE;
    }

    private AnimationsController animationsController;
    private LinearLayout [] menuItems = new  LinearLayout[5];
    private TextView [] menuItemsDescrpt = new TextView[5];
    private ImageView [] menuItemsIcon = new ImageView[5];
    private LinearLayout menuItemRootOpened;
    private ImageView openMenuButton;
    private FrameLayout fragmentContainer;
    private Fragment [] fragments;
    private FragmentManager fragmentManager;


    public MenuLibraryClass(Context context) {
        super(context);
        initComponentsFromXML();
        animationsController = new AnimationsController(menuItemRootOpened,menuItems);
        setOnClickListeners();
    }

    public MenuLibraryClass(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponentsFromXML();
        animationsController = new AnimationsController(menuItemRootOpened,menuItems);
        setOnClickListeners();
    }

    public MenuLibraryClass(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponentsFromXML();
        animationsController = new AnimationsController(menuItemRootOpened,menuItems);
        setOnClickListeners();
    }

    public void setFragmentsAndFragmentManager (@Nullable Fragment[] fragments,@Nullable FragmentManager fragmentManager){
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;

    }

    public void setImagesForMenuItems(ArrayList<Integer> imagesIds){
        for(int i = 0; i < menuItemsIcon.length;i++){
            menuItemsIcon[i].setImageResource(imagesIds.get(i));
        }
    }

    public void setImagesForMenuItems(int [] imagesIds){
        for(int i = 0; i < menuItemsIcon.length;i++){
            menuItemsIcon[i].setImageResource(imagesIds[i]);
        }
    }

    public void setImagesForMenuItems(Drawable[] drawables){
        for(int i = 0; i < menuItemsIcon.length;i++){
            menuItemsIcon[i].setImageDrawable(drawables[i]);
        }
    }

    public void setDescriptionForMenuItems(String[] strings){
        for(int i = 0; i < menuItemsDescrpt.length;i++){
            menuItemsDescrpt[i].setText(strings[i]);
        }
    }

    public void setDescriptionForMenuItems(int[] stringsIds){
        for(int i = 0; i < menuItemsDescrpt.length;i++){
            menuItemsDescrpt[i].setText(stringsIds[i]);
        }
    }


    public void setImageForFirstMenuItem(@DrawableRes int imageId){
        menuItemsIcon[0].setImageResource(imageId);
    }

    public void setImageForSecondMenuItem(@DrawableRes int imageId){
        menuItemsIcon[1].setImageResource(imageId);
    }

    public void setImageForThirdMenuItem(@DrawableRes int imageId){
        menuItemsIcon[2].setImageResource(imageId);
    }

    public void setImageForFourthMenuItem(@DrawableRes int imageId){
        menuItemsIcon[3].setImageResource(imageId);
    }

    public void setImageForFifthMenuItem(@DrawableRes int imageId){
        menuItemsIcon[4].setImageResource(imageId);
    }


    public void setDescriptionForFirstMenuItem(@StringRes int stringResId){
        menuItemsDescrpt[0].setText(stringResId);
    }

    public void setDescriptionForSecondMenuItem(@StringRes int stringResId){
        menuItemsDescrpt[1].setText(stringResId);
    }

    public void setDescriptionForThirdMenuItem(@StringRes int stringResId){
        menuItemsDescrpt[2].setText(stringResId);
    }

    public void setDescriptionForFourthMenuItem(@StringRes int stringResId){
        menuItemsDescrpt[3].setText(stringResId);
    }

    public void setDescriptionForFifthMenuItem(@StringRes int stringResId){
        menuItemsDescrpt[4].setText(stringResId);
    }

    public void setDescriptionForFirstMenuItem(String string){
        menuItemsDescrpt[0].setText(string);
    }

    public void setDescriptionForSecondMenuItem(String string){
        menuItemsDescrpt[1].setText(string);
    }

    public void setDescriptionForThirdMenuItem(String string){
        menuItemsDescrpt[2].setText(string);
    }

    public void setDescriptionForFourthMenuItem(String string){
        menuItemsDescrpt[3].setText(string);
    }

    public void setDescriptionForFifthMenuItem(String string){
        menuItemsDescrpt[4].setText(string);
    }



    private void changeMenuState(MenuCommand menuCommand){
        if(menuCommand == MenuCommand.OPEN ) {
            menuItemRootOpened.setVisibility(VISIBLE);
            for (int i = 0; i < menuItems.length; i++) {
                menuItems[i].setClickable(true);
                menuItems[i].setVisibility(VISIBLE);
            }
        }else {
            for (int i = 0; i < menuItems.length; i++) {
                menuItems[i].setClickable(false);
                menuItems[i].setVisibility(GONE);
            }
            menuItemRootOpened.setVisibility(GONE);
        }

    }

    private void setOnClickListeners(){
        openMenuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                changeMenuState(MenuCommand.OPEN);
                animationsController.showMenu();
            }
        });

        for(int i = 0; i < menuItems.length;i++){
            final int pos = i;
            menuItems[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentManager.beginTransaction().replace(R.id.frame_fragment_container,fragments[pos]).commit();
//                    changeMenuState(MenuCommand.CLOSE);
                    animationsController.hideMenu();
                }
            });
        }
    }

    private void initComponentsFromXML(){
       inflate(getContext(),R.layout.menu_layout_closed,this);
        inflate(getContext(),R.layout.menu_layout_opened,this);
        openMenuButton = (ImageButton) findViewById(R.id.button_open_menu);
        fragmentContainer = findViewById(R.id.frame_fragment_container);
        menuItemRootOpened = findViewById(R.id.main_menu_root_open);
        menuItems[4] = findViewById(R.id.item_menu_5);
        menuItems[3] = findViewById(R.id.item_menu_4);
        menuItems[2] = findViewById(R.id.item_menu_3);
        menuItems[1] = findViewById(R.id.item_menu_2);
        menuItems[0] = findViewById(R.id.item_menu_1);
        menuItemsDescrpt[0] = findViewById(R.id.menu_item_1_description);
        menuItemsDescrpt[1] = findViewById(R.id.menu_item_2_description);
        menuItemsDescrpt[2] = findViewById(R.id.menu_item_3_description);
        menuItemsDescrpt[3] = findViewById(R.id.menu_item_4_description);
        menuItemsDescrpt[4] = findViewById(R.id.menu_item_5_description);
        menuItemsIcon[0] = findViewById(R.id.menu_item_1_image);
        menuItemsIcon[1] = findViewById(R.id.menu_item_2_image);
        menuItemsIcon[2] = findViewById(R.id.menu_item_3_image);
        menuItemsIcon[3] = findViewById(R.id.menu_item_4_image);
        menuItemsIcon[4] = findViewById(R.id.menu_item_5_image);


    }

}
