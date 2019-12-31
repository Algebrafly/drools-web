-- 1.据场景信息获取相关的实体信息-->> 组装drl文件的import语句
SELECT DISTINCT
	E.ENTITY_ID,
	E.ENTITY_NAME,
	E.ENTITY_DESC,
	E.ENTITY_IDENTIFY,
	E.PKG_NAME,
	E.CRE_USER_ID,
	E.CRE_TIME,
	E.IS_EFFECT,
	E.REMARK 
FROM
	RULE_ENTITY_INFO E
	INNER JOIN RULE_SCENE_ENTITY_REL SER ON E.ENTITY_ID = SER.ENTITY_ID
	INNER JOIN RULE_SCENE_INFO S ON S.SCENE_ID = SER.SCENE_ID 
WHERE
	1 = 1 
	AND E.IS_EFFECT = 1 
	AND S.IS_EFFECT = 1 
	AND S.SCENE_IDENTIFY = 'test' 
	
	-- 1.导入场景对应实体类；2.导入基本类；3.导入动作类
	-- 根据场景获取动作类信息 -->> 组装import语句中动作类
	SELECT DISTINCT
        A.ACTION_ID,
        A.ACTION_TYPE,
        A.ACTION_NAME,
        A.ACTION_DESC,
        A.ACTION_CLASS,
        A.IS_EFFECT,
        A.CRE_USER_ID,
        A.CRE_TIME,
        A.REMARK
        FROM
        RULE_ACTION_INFO A
        INNER JOIN RULE_ACTION_RULE_REL AR ON AR.ACTION_ID = A.ACTION_ID
        INNER JOIN RULE_INFO R ON R.RULE_ID = AR.RULE_ID
        INNER JOIN RULE_SCENE_INFO S ON S.SCENE_ID = R.SCENE_ID
        WHERE
        1 = 1
        AND A.IS_EFFECT = 1
        AND AR.IS_EFFECT = 1
        AND R.IS_EFFECT = 1
        AND R.RULE_ENABLED = 1
        AND S.IS_EFFECT = 1
        AND S.SCENE_IDENTIFY = 'test'
	
	
-- 2.根据场景获取对应的规则规则信息 -->> 拼接rule块
SELECT DISTINCT
	R.RULE_ID,
	R.SCENE_ID,
	R.RULE_NAME,
	R.RULE_DESC,
	R.RULE_ENABLED,
	R.IS_EFFECT,
	R.CRE_USER_ID,
	R.CRE_TIME,
	R.REMARK 
FROM
	RULE_INFO R
	INNER JOIN RULE_SCENE_INFO S ON S.SCENE_ID = R.SCENE_ID 
WHERE
	1 = 1 
	AND S.IS_EFFECT = 1 
	AND R.IS_EFFECT = 1 
	AND R.RULE_ENABLED = 1 
	AND S.SCENE_IDENTIFY = 'test'
	
	-- 据规则拼接规则自身相关的属性信息
	SELECT DISTINCT
        RPR.RULE_PRO_REL_ID,
        RPR.RULE_ID,
        RPR.RULE_PROPERTY_ID,
        RPR.RULE_PROPERTY_VALUE,
        T.RULE_PROPERTY_IDENTIFY,
        T.RULE_PROPERTY_NAME,
        T.RULE_PROPERTY_DESC,
        T.DEFAULT_VALUE,
        T.IS_EFFECT,
        T.REMARK
        FROM
        RULE_PROPERTY_INFO T
        INNER JOIN RULE_PROPERTY_REL RPR ON RPR.RULE_PROPERTY_ID = T.RULE_PROPERTY_ID
        WHERE
        1 = 1
        AND T.IS_EFFECT = 1
        AND RPR.RULE_ID = '1'
	
	-- 拼接规则条件信息:拼接when，拼接动作条件，拼接普通条件
	SELECT DISTINCT
        *
        FROM
        RULE_CONDITION_INFO T
        WHERE
        1 = 1
				AND T.RULE_ID = '1'
        
	
	-- 拼接规则动作部分:拼接then
	SELECT DISTINCT
                A.ACTION_ID,
                A.ACTION_TYPE,
                A.ACTION_NAME,
                A.ACTION_DESC,
                A.ACTION_CLASS,
                A.IS_EFFECT,
                A.CRE_USER_ID,
                A.CRE_TIME,
                A.REMARK
            FROM
                RULE_ACTION_INFO A
            INNER JOIN RULE_ACTION_RULE_REL AR ON AR.ACTION_ID = A.ACTION_ID
            WHERE
                1 = 1
            AND A.IS_EFFECT = 1
            AND AR.IS_EFFECT = 1
            AND AR.RULE_ID = '1'
						
			-- 根据动作id获取动作参数信息
			SELECT DISTINCT
						*
						FROM
						RULE_ACTION_PARAM_INFO T
						WHERE
						1 = 1
						AND T.IS_EFFECT = 1
						AND T.ACTION_ID = '2'
						
			-- 根据参数id获取参数value
			SELECT DISTINCT
        *
        FROM
        RULE_ACTION_PARAM_VALUE_INFO T
        WHERE
        1 = 1
        AND T.IS_EFFECT = 1
        AND T.ACTION_PARAM_ID = '1'
	
	
	
	
	
	