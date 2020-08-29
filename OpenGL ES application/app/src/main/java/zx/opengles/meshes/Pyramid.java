package zx.opengles.meshes;

public class Pyramid extends BaseMesh {

    public Pyramid() {

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

        final float[] colourData = {
                // Ściana przednia (czerwona)
                1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f,

                // Ściana prawa (zielona)
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,

                // Ściana tylna (niebieska)
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                // Ściana lewa (żółta)
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f

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
        colourBuffer = createBuffer(colourData);
        normalBuffer = createBuffer(normalData);
    }
}
