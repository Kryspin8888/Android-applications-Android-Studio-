package zx.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 @author Marek Kulawiak
 */

class ESSurfaceView extends GLSurfaceView
{
	protected GLRenderer renderer=null;

    public ESSurfaceView(Context context)
    {
        super(context);

        // Stworzenie kontekstu OpenGL ES 2.0.
        setEGLContextClientVersion(2);

        // Przypisanie renderera do widoku.
        renderer = new GLRenderer();
        renderer.setContext(getContext());
        setRenderer(renderer);
    }
    
    public GLRenderer getRenderer()
    {
    	return renderer;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        if (event != null)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if (renderer != null)
                {
                    // Ensure we call switchMode() on the OpenGL thread.
                    // queueEvent() is a method of GLSurfaceView that will do this for us.
                    queueEvent(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            renderer.switchMode(event.getX(), event.getY());
                        }
                    });

                    return true;
                }
            }
        }

        return super.onTouchEvent(event);
    }
}
