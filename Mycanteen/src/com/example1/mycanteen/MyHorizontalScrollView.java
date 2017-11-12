package com.example1.mycanteen;

import java.util.HashMap;  
import java.util.Map;  
  
import android.content.Context;  
import android.graphics.Color;  
import android.util.AttributeSet;  
import android.util.DisplayMetrics;  
import android.util.Log;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.WindowManager;  
import android.widget.HorizontalScrollView;  
import android.widget.LinearLayout;  
  
public class MyHorizontalScrollView extends HorizontalScrollView implements  
        OnClickListener  
{  
  
    /** 
     * ͼƬ����ʱ�Ļص��ӿ� 
     *  
     * @author zhy 
     *  
     */  
    public interface CurrentImageChangeListener  
    {  
        void onCurrentImgChanged(int position, View viewIndicator);  
    }  
  
    /** 
     * ��Ŀ���ʱ�Ļص� 
     *  
     * @author zhy 
     *  
     */  
    public interface OnItemClickListener  
    {  
        void onClick(View view, int pos);  
    }  
  
    private CurrentImageChangeListener mListener;  
  
    private OnItemClickListener mOnClickListener;  
  
    private static final String TAG = "MyHorizontalScrollView";  
  
    /** 
     * HorizontalListView�е�LinearLayout 
     */  
    private LinearLayout mContainer;  
  
    /** 
     * ��Ԫ�صĿ�� 
     */  
    private int mChildWidth;  
    /** 
     * ��Ԫ�صĸ߶� 
     */  
    private int mChildHeight;  
    /** 
     * ��ǰ���һ��ͼƬ��index 
     */  
    private int mCurrentIndex;  
    /** 
     * ��ǰ��һ��ͼƬ���±� 
     */  
    private int mFristIndex;  
    /** 
     * ��ǰ��һ��View 
     */  
    private View mFirstView;  
    /** 
     * ���������� 
     */  
    private HorizontalScrollViewAdapter mAdapter;  
    /** 
     * ÿ��Ļ�����ʾ�ĸ��� 
     */  
    private int mCountOneScreen;  
    /** 
     * ��Ļ�Ŀ�� 
     */  
    private int mScreenWitdh;  
  
  
    /** 
     * ����View��λ�õļ�ֵ�� 
     */  
    private Map<View, Integer> mViewPos = new HashMap<View, Integer>();  
  
    public MyHorizontalScrollView(Context context, AttributeSet attrs)  
    {  
        super(context, attrs);  
        // �����Ļ���  
        WindowManager wm = (WindowManager) context  
                .getSystemService(Context.WINDOW_SERVICE);  
        DisplayMetrics outMetrics = new DisplayMetrics();  
        wm.getDefaultDisplay().getMetrics(outMetrics);  
        mScreenWitdh = outMetrics.widthPixels;  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)  
    {  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
        mContainer = (LinearLayout) getChildAt(0);  
    }  
  
    /** 
     * ������һ��ͼƬ 
     */  
    protected void loadNextImg()  
    {  
        // ����߽�ֵ����  
        if (mCurrentIndex == mAdapter.getCount() - 1)  
        {  
            return;  
        }  
        //�Ƴ���һ��ͼƬ���ҽ�ˮƽ����λ����0  
        scrollTo(0, 0);  
        mViewPos.remove(mContainer.getChildAt(0));  
        mContainer.removeViewAt(0);  
          
        //��ȡ��һ��ͼƬ����������onclick�¼����Ҽ���������  
        View view = mAdapter.getView(++mCurrentIndex, null, mContainer);  
        view.setOnClickListener(this);  
        mContainer.addView(view);  
        mViewPos.put(view, mCurrentIndex);  
          
        //��ǰ��һ��ͼƬС��  
        mFristIndex++;  
        //��������˹��������򴥷�  
        if (mListener != null)  
        {  
            notifyCurrentImgChanged();  
        }  
  
    }  
    /** 
     * ����ǰһ��ͼƬ 
     */  
    protected void loadPreImg()  
    {  
        //�����ǰ�Ѿ��ǵ�һ�ţ��򷵻�  
        if (mFristIndex == 0)  
            return;  
        //��õ�ǰӦ����ʾΪ��һ��ͼƬ���±�  
        int index = mCurrentIndex - mCountOneScreen;  
        if (index >= 0)  
        {  
//          mContainer = (LinearLayout) getChildAt(0);  
            //�Ƴ����һ��  
            int oldViewPos = mContainer.getChildCount() - 1;  
            mViewPos.remove(mContainer.getChildAt(oldViewPos));  
            mContainer.removeViewAt(oldViewPos);  
              
            //����View�����һ��λ��  
            View view = mAdapter.getView(index, null, mContainer);  
            mViewPos.put(view, index);  
            mContainer.addView(view, 0);  
            view.setOnClickListener(this);  
            //ˮƽ����λ�������ƶ�view�Ŀ�ȸ�����  
            scrollTo(mChildWidth, 0);  
            //��ǰλ��--����ǰ��һ����ʾ���±�--  
            mCurrentIndex--;  
            mFristIndex--;  
            //�ص�  
            if (mListener != null)  
            {  
                notifyCurrentImgChanged();  
  
            }  
        }  
    }  
  
    /** 
     * ����ʱ�Ļص� 
     */  
    public void notifyCurrentImgChanged()  
    {  
        //��������еı���ɫ�����ʱ������Ϊ��ɫ  
        for (int i = 0; i < mContainer.getChildCount(); i++)  
        {  
            mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);  
        }  
          
        mListener.onCurrentImgChanged(mFristIndex, mContainer.getChildAt(0));  
  
    }  
  
    /** 
     * ��ʼ�����ݣ��������������� 
     *  
     * @param mAdapter 
     */  
    public void initDatas(HorizontalScrollViewAdapter mAdapter)  
    {  
        this.mAdapter = mAdapter;  
        mContainer = (LinearLayout) getChildAt(0);  
        // ����������е�һ��View  
        final View view = mAdapter.getView(0, null, mContainer);  
        mContainer.addView(view);  
  
        // ǿ�Ƽ��㵱ǰView�Ŀ�͸�  
        if (mChildWidth == 0 && mChildHeight == 0)  
        {  
            int w = View.MeasureSpec.makeMeasureSpec(0,  
                    View.MeasureSpec.UNSPECIFIED);  
            int h = View.MeasureSpec.makeMeasureSpec(0,  
                    View.MeasureSpec.UNSPECIFIED);  
            view.measure(w, h);  
            mChildHeight = view.getMeasuredHeight();  
            mChildWidth = view.getMeasuredWidth();  
            Log.e(TAG, view.getMeasuredWidth() + "," + view.getMeasuredHeight());  
            mChildHeight = view.getMeasuredHeight();  
            // ����ÿ�μ��ض��ٸ�View  
            mCountOneScreen = mScreenWitdh / mChildWidth+2;  
            if(mCountOneScreen>mAdapter.getCount()){
              mCountOneScreen=mAdapter.getCount();
              }
            Log.e(TAG, "mCountOneScreen = " + mCountOneScreen  
                    + " ,mChildWidth = " + mChildWidth);  
              
  
        }  
        //��ʼ����һ��Ļ��Ԫ��  
        initFirstScreenChildren(mCountOneScreen);  
    }  
  
    /** 
     * ���ص�һ����View 
     *  
     * @param mCountOneScreen 
     */  
    public void initFirstScreenChildren(int mCountOneScreen)  
    {  
        mContainer = (LinearLayout) getChildAt(0);  
        mContainer.removeAllViews();  
        mViewPos.clear();  
  
        for (int i = 0; i < mCountOneScreen; i++)  
        {  
            View view = mAdapter.getView(i, null, mContainer);  
            view.setOnClickListener(this);  
            mContainer.addView(view);  
            mViewPos.put(view, i);  
            mCurrentIndex = i;  
        }  
  
        if (mListener != null)  
        {  
            notifyCurrentImgChanged();  
        }  
  
    }  
  
    @Override  
    public boolean onTouchEvent(MotionEvent ev)  
    {  
        switch (ev.getAction())  
        {  
        case MotionEvent.ACTION_MOVE:  
//          Log.e(TAG, getScrollX() + "");  
  
            int scrollX = getScrollX();  
            // �����ǰscrollXΪview�Ŀ�ȣ�������һ�ţ��Ƴ���һ��  
            if (scrollX >= mChildWidth)  
            {  
                loadNextImg();  
            }  
            // �����ǰscrollX = 0�� ��ǰ����һ�ţ��Ƴ����һ��  
            if (scrollX == 0)  
            {  
                loadPreImg();  
            }  
            break;  
        }  
        return super.onTouchEvent(ev);  
    }  
  
    @Override  
    public void onClick(View v)  
    {  
        if (mOnClickListener != null)  
        {  
            for (int i = 0; i < mContainer.getChildCount(); i++)  
            {  
                mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);  
            }  
            mOnClickListener.onClick(v, mViewPos.get(v));  
        }  
    }  
  
    public void setOnItemClickListener(OnItemClickListener mOnClickListener)  
    {  
        this.mOnClickListener = mOnClickListener;  
    }  
  
    public void setCurrentImageChangeListener(  
            CurrentImageChangeListener mListener)  
    {  
        this.mListener = mListener;  
    }  
  
}  
