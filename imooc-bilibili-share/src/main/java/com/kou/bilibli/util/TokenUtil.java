package com.kou.bilibli.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.kou.bilibli.exception.ConditionException;

import java.util.Calendar;
import java.util.Date;

/**
 * @author KouChaoJie
 * @since: 2022/7/7 16:09
 */
public class TokenUtil {
    private static final String ISSUER = "Kou";

    /**
     * 生成token令牌
     *
     * @param userId userId
     * @return 令牌
     */
    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 60);
        return JWT.create()
                .withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public static Long verifyToken(String token) {

        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e) {
            //token过期
            throw new ConditionException("555", "token过期");
        } catch (Exception e) {
            throw new ConditionException("非法用户token");
        }
    }
}
