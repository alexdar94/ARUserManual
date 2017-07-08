package com.tp034766.arusermanual;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by User on 5/20/2017.
 */

public class ModelSurfaceView extends GLSurfaceView {

    private ARUserManualActivity parent;
    private ModelRenderer mRenderer;

    public ModelSurfaceView(Context context){
        super(context);
    }

    public ModelSurfaceView(Context context, AttributeSet attribs) {
        super(context, attribs);

        this.parent = (ARUserManualActivity)context;
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        // Set surfaceView background to transparent
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        mRenderer = new ModelRenderer(parent);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public ARUserManualActivity getModelActivity() {
        return parent;
    }

    public ModelRenderer getRenderer(){
        return mRenderer;
    }
}