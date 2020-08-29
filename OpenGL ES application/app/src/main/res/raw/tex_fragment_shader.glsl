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
    vec3 lightPosition = vec3(0.0, 2.0, 1.0);

    // Długość wektora światła padającego na model w danym fragmencie.
    float distance = length(lightPosition - interpolatedPosition);

    // Znormalizowany wektor kierunku padania światła.
    vec3 lightVector = normalize(lightPosition - interpolatedPosition);

    // Wyznaczenie siły oddziaływania światła za pomocą iloczynu skalarnego.
    // Im mniejsza różnica pomiędzy kątem padania światła a normalną model w danym fragmencie,
    // tym silniejsze oddziaływanie światła.
    float diffuse = max( dot(interpolatedNormal, lightVector), 0.0 );

    // Wytłumienie siły oddziaływania światła wraz ze wzrostem odległości.
    diffuse = diffuse * (1.0 / (1.0 + (0.10 * distance)));

    // Doświetlenie modelu światłem ogólnym ("ambient light").
    diffuse = diffuse  + 0.2;

    vec3 vReflection = normalize( reflect( -lightVector, interpolatedNormal ) );
    float specularIntensity = max( 0.0, dot( normalize( interpolatedNormal ), normalize ( vReflection ) ) );
    float fSpecular = pow(specularIntensity, specExp);
    vec3 specColor = fSpecular * vec3(1.0,1.0,1.0);

    // Wyznaczenie koloru fragmentu poprzez przemnożenie zinterpolowanego koloru modelu w danym fragmencie
    // przez siłę oddziaływania światła oraz kolor na teksturze.
    gl_FragColor = diffuse * lightColour * texture2D(diffuseTexture, interpolatedTexCoord) ;
    gl_FragColor.rgb += specColor;
    gl_FragColor.a = 1.0;

    if(gl_FragColor.x >= 0.2 && gl_FragColor.y >= 0.1 && gl_FragColor.z <= 0.5 )
        gl_FragColor.a = 0.0;
}
