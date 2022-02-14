package com.velocia.accountapi.data.response;

public record LoginResponse(
        Long accountUUID //TODO 굳이 practice 인데 jwt 유틸까지 구현할 필요는 없다고 생각한다.
) {
}
