package zx.opengles.meshes;

public class TexturedPyramidMesh extends BaseMesh {

    public TexturedPyramidMesh() {

        final float[] positionData = {
                // Przednia ściana
                0.5f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.5f,
                -0.5f, 0.0f, 1.0f,

                // Prawa ściana
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.5f,
                0.5f, 0.0f, 1.0f,

                // Lewa ściana
                0.0f, 0.0f, 0.0f,
                -0.5f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.5f,


                // Dolna ściana
                -0.5f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.0f,
                0.5f, 0.0f, 1.0f

        };

        final float[] texCoordData = {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,

                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f
        };

        final float[] normalData = {
                // Przednia ściana
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                // Prawa ściana
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

                // Tylna ściana
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,


                // Dolna ściana
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f

        };

        numberOfVertices = 12;

        positionBuffer = createBuffer(positionData);
        texCoordsBuffer = createBuffer(texCoordData);
        normalBuffer = createBuffer(normalData);

    }
}
