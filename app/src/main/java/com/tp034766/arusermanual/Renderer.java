package com.tp034766.arusermanual;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.animation.EllipticalOrbitAnimation3D;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by User on 5/28/2017.
 */

public class Renderer extends RajawaliRenderer {
    private Context context;
    private PointLight mLight;
    private Object3D mObjectGroup;
    private Animation3D mCameraAnim, mLightAnim;
    private SurfaceView surfaceView;
    private static final double rtod = 180 / Math.PI;

    public Renderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    public Renderer(Context context, boolean registerForResources) {
        super(context, registerForResources);
    }

    public void onOffsetsChanged(float x, float y, float z, float w, int i, int j){
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    protected void initScene() {
        mLight = new PointLight();
        mLight.setPosition(0, 0, 4);
        mLight.setPower(3);

        getCurrentScene().addLight(mLight);
        getCurrentCamera().setZ(16);

        LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(),
                mTextureManager, R.raw.bremenmask_mtl);

        try {
            objParser.parse();
            mObjectGroup = objParser.getParsedObject();
            getCurrentScene().addChild(mObjectGroup);

            mCameraAnim = new RotateOnAxisAnimation(Vector3.Axis.Y, 360);
            mCameraAnim.setDurationMilliseconds(8000);
            mCameraAnim.setRepeatMode(Animation.RepeatMode.INFINITE);
            mCameraAnim.setTransformable3D(mObjectGroup);
        } catch (ParsingException e) {
            e.printStackTrace();
        }

        mLightAnim = new EllipticalOrbitAnimation3D(new Vector3(),
                new Vector3(0, 10, 0), Vector3.getAxisVector(Vector3.Axis.Z), 0,
                360, EllipticalOrbitAnimation3D.OrbitDirection.CLOCKWISE);

        mLightAnim.setDurationMilliseconds(3000);
        mLightAnim.setRepeatMode(Animation.RepeatMode.INFINITE);
        mLightAnim.setTransformable3D(mLight);

        getCurrentScene().registerAnimation(mCameraAnim);
        getCurrentScene().registerAnimation(mLightAnim);

        //mCameraAnim.play();
        //mLightAnim.play();
    }

    public void setSurfaceView(SurfaceView surfaceView){
        this.surfaceView = surfaceView;
    }

    public void set3DObjectPosition(double x, double y, double z) {
        if (mObjectGroup != null)
            mObjectGroup.setPosition(x, y, z);
    }

    public Vector3 get3DObjectPosition() {
        return mObjectGroup.getPosition();
    }

    public void setCameraPosition(double x, double y, double z) {
        getCurrentCamera().setX(x);
        getCurrentCamera().setY(y);
        getCurrentCamera().setZ(z);
    }

    public void setCamLRTilt(double lrTiltAngleInRadians) {
        getCurrentCamera().setRotZ(-lrTiltAngleInRadians * rtod);

    }

    public void setCamFBTilt(double fbTiltAngleInRadians) {
        getCurrentCamera().setRotX(-fbTiltAngleInRadians * rtod);

    }

    public void setCubeSize(double d) {
        mObjectGroup.setScale(d);
    }

    public Vector3 getCubeSize() {
        return mObjectGroup.getScale();
    }

    public void setLRTilt(double lrTiltAngleInRadians) {
        mObjectGroup.setRotZ(-lrTiltAngleInRadians * rtod);

    }

    public void setFBTilt(double fbTiltAngleInRadians) {
        mObjectGroup.setRotX(-fbTiltAngleInRadians * rtod);

    }

    public void setSpin(double spinAngleInDegrees) {
        mObjectGroup.setRotY(mObjectGroup.getRotY() + spinAngleInDegrees);
    }

    public boolean isReady() {
        return (mObjectGroup != null);
    }
}