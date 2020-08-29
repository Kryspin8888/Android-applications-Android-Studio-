// Zmniejszenie domyślnego poziomu precyzji.
precision mediump float;

uniform sampler2D diffuseTexture;

// Zmienne przekazane z vertex shadera.
varying vec2 interpolatedTexCoord;

varying vec3 interpolatedPosition;
varying vec3 interpolatedNormal;
uniform vec4 lightColour;
uniform float specExp;

void main()
{
    vec3 lightPosition = vec3(0.0, 2.0, -1.0);

    // Długość wektora światła padającego na model w danym fragmencie.
    float distance = length(lightPosition - interpolatedPosition);

    // Znormalizowany wektor kierunku padania światła.
    vec3 lightVector = normalize(lightPosition - interpolatedPosition);

    // Wyznaczenie siły oddziaływania światła za pomocą iloczynu skalarnego.
    // Im mniejsza różnica pomiędzy kątem padania światła a normalną model w danym fragmencie,
    // tym silniejsze oddziaływanie światła.
    float diffuse = max( dot(interpolatedNormal, lightVector), 0.0 );


    // Doświetlenie modelu światłem ogólnym ("ambient light").

    // Wyznaczenie koloru fragmentu poprzez przemnożenie zinterpolowanego koloru modelu w danym fragmencie
    // przez siłę oddziaływania światła oraz kolor na teksturze.
    gl_FragColor = diffuse * texture2D(diffuseTexture, interpolatedTexCoord);

    gl_FragColor.a = (gl_FragColor.r * gl_FragColor.g * gl_FragColor.b) ;

}
