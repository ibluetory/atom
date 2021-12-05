package com.blue.atom.util;

import com.blue.atom.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 基于auth0-jwt工具类
 *
 * @author ligw
 */
@Component
public class AuthJwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthJwtUtil.class);

    @Resource
    HttpServletRequest request;

    /**
     * header-key
     */
    @Value("${jwt.header}")
    public String tokenHeader;

    /**
     * 密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时长(毫秒)
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 签发者
     */
    @Value("${jwt.issuer}")
    private String issuer;

    /**
     * 生成token
     *
     * @return
     */
    public String generateToken(User user) {
        Date currentDate = new Date();
        Date expireDate = DateUtils.addMilliseconds(currentDate, expiration.intValue());

        try {
            // 执行sign生成token
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            return JWT.create().withAudience(user.getId().toString()).withIssuer(issuer).withExpiresAt(expireDate).sign(algorithm);
        } catch (JWTCreationException e) {
            LOGGER.error("", e.getMessage());
            return null;
        }
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(tokenHeader);
    }

    /**
     * 获取userId
     *
     * @param token
     * @return
     */
    public String getUserId(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    /**
     * 获取userId
     *
     * @return
     */
    public int getUserId() {
        String token = request.getHeader(tokenHeader);
        String userId = getUserId(token);
        return Integer.parseInt(userId);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        Date currentDate = new Date();
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim expClaim = jwt.getClaims().get("exp");

            if (expClaim == null || currentDate.after(expClaim.asDate())) {
                return false;
            }
            return true;
        } catch (JWTVerificationException e) {
            LOGGER.error("", e.getMessage());
            return false;
        }
    }
}
