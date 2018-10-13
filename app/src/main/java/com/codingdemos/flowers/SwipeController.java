package com.codingdemos.flowers;

//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.helper.ItemTouchHelper.Callback;
//import android.view.MotionEvent;
//import android.view.View;
//
//import static android.support.v7.widget.helper.ItemTouchHelper.*;
//
//public class SwipeController extends Callback {
//
//    private boolean swipeBack = false;
//    private ButtonsState buttonShowedState = ButtonsState.GONE;
//    private static final float buttonWidth = 300;
//
//    @Override
//    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        return makeMovementFlags(0, LEFT | RIGHT);
//    }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//
//    enum ButtonsState {
//        GONE,
//        LEFT_VISIBLE,
//        RIGHT_VISIBLE
//    }
//
//    @Override
//    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
//        if (swipeBack) {
//            swipeBack = false;
//            return 0;
//        }
//        return super.convertToAbsoluteDirection(flags, layoutDirection);
//    }
//
//    @Override
//    public void onChildDraw(Canvas c,
//                            RecyclerView recyclerView,
//                            RecyclerView.ViewHolder viewHolder,
//                            float dX, float dY,
//                            int actionState, boolean isCurrentlyActive) {
//
//        if (actionState == ACTION_STATE_SWIPE) {
//            setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//    }
//
//    private void setTouchListener(Canvas c,
//                                  RecyclerView recyclerView,
//                                  RecyclerView.ViewHolder viewHolder,
//                                  float dX, float dY,
//                                  int actionState, boolean isCurrentlyActive) {
//
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
//                return false;
//            }
//        });
//    }
////
////
////
////    private void setTouchListener(final Canvas c,
////                                  final RecyclerView recyclerView,
////                                  final RecyclerView.ViewHolder viewHolder,
////                                  final float dX, final float dY,
////                                  final int actionState, final boolean isCurrentlyActive) {
////        recyclerView.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
////                if (swipeBack) {
////                    if (dX < -buttonWidth) buttonShowedState = ButtonsState.RIGHT_VISIBLE;
////                    else if (dX > buttonWidth) buttonShowedState  = ButtonsState.LEFT_VISIBLE;
////
////                    if (buttonShowedState != ButtonsState.GONE) {
////                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
////                        setItemsClickable(recyclerView, false);
////                    }
////                }
////                return false;
////            }
////        });
////    }
//
//
//
//    // SwipeController.java
//    private void setTouchDownListener(final Canvas c,
//                                      final RecyclerView recyclerView,
//                                      final RecyclerView.ViewHolder viewHolder,
//                                      final float dX, final float dY,
//                                      final int actionState, final boolean isCurrentlyActive) {
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                }
//                return false;
//            }
//        });
//    }
//
//    private void setTouchUpListener(final Canvas c,
//                                    final RecyclerView recyclerView,
//                                    final RecyclerView.ViewHolder viewHolder,
//                                    final float dX, final float dY,
//                                    final int actionState, final boolean isCurrentlyActive) {
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    SwipeController.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
//                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            return false;
//                        }
//                    });
//                    setItemsClickable(recyclerView, true);
//                    swipeBack = false;
//                    buttonShowedState = ButtonsState.GONE;
//                }
//                return false;
//            }
//        });
//    }
//
//    private void setItemsClickable(RecyclerView recyclerView,
//                                   boolean isClickable) {
//        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
//            recyclerView.getChildAt(i).setClickable(isClickable);
//        }
//    }
//
//
////    // SwipeController.java
////    @Override
////    public void onChildDraw(Canvas c,
////                            RecyclerView recyclerView,
////                            RecyclerView.ViewHolder viewHolder,
////                            float dX, float dY,
////                            int actionState, boolean isCurrentlyActive) {
////        // ...
////        drawButtons(c, viewHolder);
////    }
////
////
//    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
//        float buttonWidthWithoutPadding = buttonWidth - 20;
//        float corners = 16;
//
//        View itemView = viewHolder.itemView;
//        Paint p = new Paint();
//
//        RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
//        p.setColor(Color.BLUE);
//        c.drawRoundRect(leftButton, corners, corners, p);
//        drawText("EDIT", c, leftButton, p);
//
//        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//        p.setColor(Color.RED);
//        c.drawRoundRect(rightButton, corners, corners, p);
//        drawText("DELETE", c, rightButton, p);
//
//        buttonInstance = null;
//        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//            buttonInstance = leftButton;
//        }
//        else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
//            buttonInstance = rightButton;
//        }
//    }
//
//    private void drawText(String text, Canvas c, RectF button, Paint p) {
//        float textSize = 60;
//        p.setColor(Color.WHITE);
//        p.setAntiAlias(true);
//        p.setTextSize(textSize);
//
//        float textWidth = p.measureText(text);
//        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);
//    }
//
////    // SwipeController.java
////    private RecyclerView.ViewHolder currentItemViewHolder = null;
////if (buttonShowedState == ButtonsState.GONE) {
////        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
////    }
////    currentItemViewHolder = viewHolder;
////}// PlayersSwipeController.java
////    private RecyclerView.ViewHolder currentItemViewHolder = null;
////
//
//    // SwipeController.java
//    public SwipeController(SwipeControllerActions buttonsActions) {
//        this.buttonsActions = buttonsActions;
//    }
//
////    // SwipeController.java
////if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
////        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
////            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
////        }
////        else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
////            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());
////        }
////    }
//
//}



import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.codingdemos.flowers.SwipeControllerActions;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

enum ButtonsState {
    GONE,
//    LEFT_VISIBLE
//    ,
    RIGHT_VISIBLE
}

public class SwipeController extends Callback {

    private boolean swipeBack = false;

    private ButtonsState buttonShowedState = ButtonsState.GONE;

    private RectF buttonInstance = null;
    private RectF buttonInstanceLeft = null;
    private RectF buttonInstanceCenter = null;

    private RecyclerView.ViewHolder currentItemViewHolder = null;

    private SwipeControllerActions buttonsActions = null;

    private static final float buttonWidth = 250;

    public SwipeController(SwipeControllerActions buttonsActions) {
        this.buttonsActions = buttonsActions;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0,
                LEFT
// |
//                RIGHT
        );
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
//                if (buttonShowedState == ButtonsState.LEFT_VISIBLE) dX = Math.max(dX, buttonWidth);
                if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) dX = Math.min(dX, -(buttonWidth * 3)-20);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
            else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        currentItemViewHolder = viewHolder;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth) buttonShowedState = ButtonsState.RIGHT_VISIBLE;
//                    else
//                        if (dX > buttonWidth) buttonShowedState  = ButtonsState.LEFT_VISIBLE;

                    if (buttonShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemsClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeController.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    setItemsClickable(recyclerView, true);
                    //swipeBack = false;
                    swipeBack = true;

                    if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
//                        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
//                        }
////                        else


                        Log.d("LOG", "ButtonsState.RIGHT_VISIBLE" + ButtonsState.RIGHT_VISIBLE);
                        Log.d("LOG", "buttonShowedState" + buttonShowedState);

                            if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
                            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());


//                                buttonsActions.onCenterClicked(viewHolder.getAdapterPosition());
////
//                                buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());

//                                if(buttonInstance==b)

                        }
                    }else
                    if (buttonsActions != null && buttonInstanceCenter != null && buttonInstanceCenter.contains(event.getX(), event.getY())) {
//                        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
//                        }
////                        else


                        Log.d("LOG", "ButtonsState.RIGHT_VISIBLE" + ButtonsState.RIGHT_VISIBLE);
                        Log.d("LOG", "buttonShowedState" + buttonShowedState);

                        if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
//                            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());


                                buttonsActions.onCenterClicked(viewHolder.getAdapterPosition());
////
//                                buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());

//                                if(buttonInstance==b)

                        }
                    } else
                    if (buttonsActions != null && buttonInstanceLeft != null && buttonInstanceLeft.contains(event.getX(), event.getY())) {
//                        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//                            buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());
//                        }
////                        else


                        Log.d("LOG", "ButtonsState.RIGHT_VISIBLE" + ButtonsState.RIGHT_VISIBLE);
                        Log.d("LOG", "buttonShowedState" + buttonShowedState);

                        if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
//                            buttonsActions.onRightClicked(viewHolder.getAdapterPosition());


//                                buttonsActions.onCenterClicked(viewHolder.getAdapterPosition());
////
                                buttonsActions.onLeftClicked(viewHolder.getAdapterPosition());

//                                if(buttonInstance==b)

                        }
                    }
                    buttonShowedState = ButtonsState.GONE;
                    currentItemViewHolder = null;
                }
                return false;
            }
        });
    }

    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable) {
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        float buttonWidthWithoutPadding = buttonWidth - 0;
        float corners = 10;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();

//        RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
//        p.setColor(Color.BLUE);
//        c.drawRoundRect(leftButton, corners, corners, p);
//        drawText("EDIT", c, leftButton, p);

//        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//        p.setColor(Color.RED);
//        c.drawRoundRect(rightButton, corners, corners, p);
//        drawText("DELETE", c, rightButton, p);

        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding -15, itemView.getTop()+20, itemView.getRight() -40, itemView.getBottom()-20);
        p.setColor(Color.RED);
        // border
//        p.setStyle(Paint.Style.STROKE);
//        p.setTextSize(R.dimen.font_normal);
        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = 18 * buttonWidth / buttonWidth;
        // Set the paint for that size.
        p.setTextSize(desiredTextSize);
        c.drawRoundRect(rightButton, corners, corners, p);
        drawText("DELETE", c, rightButton, p);

        RectF centerButton = new RectF(itemView.getRight() - (buttonWidthWithoutPadding*2) -15, itemView.getTop()+20, itemView.getRight()- (buttonWidthWithoutPadding*1) -40, itemView.getBottom()-20);
        p.setColor(Color.BLUE);
        // border
//        p.setStyle(Paint.Style.STROKE);
//        p.setTextSize(R.dimen.font_normal);
        // Calculate the desired size as a proportion of our testTextSize.
//        float desiredTextSize = 18 * buttonWidth / buttonInstance.width();
        // Set the paint for that size.
        p.setTextSize(desiredTextSize);
        c.drawRoundRect(centerButton, corners, corners, p);
        drawText("EDIT", c, centerButton, p);

        RectF leftButton = new RectF(itemView.getRight() - (buttonWidthWithoutPadding*3) -15, itemView.getTop()+20, itemView.getRight()- (buttonWidthWithoutPadding*2) -40, itemView.getBottom()-20);
        p.setColor(Color.GRAY);
        // border
//        p.setStyle(Paint.Style.STROKE);
//        p.setTextSize(R.dimen.font_normal);
        // Calculate the desired size as a proportion of our testTextSize.
//        float desiredTextSize = 18 * buttonWidth / buttonInstance.width();
        // Set the paint for that size.
        p.setTextSize(desiredTextSize);
        c.drawRoundRect(leftButton, corners, corners, p);
        drawText("CANCEL", c, leftButton, p);





        buttonInstance = null;
//        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
//            buttonInstance = leftButton;
//        }
////        else
            if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
            buttonInstance = rightButton;
                buttonInstanceLeft = leftButton;
                buttonInstanceCenter = centerButton;
        }
    }

    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 60;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);



        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);
    }

    public void onDraw(Canvas c) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder);
        }
    }
}

