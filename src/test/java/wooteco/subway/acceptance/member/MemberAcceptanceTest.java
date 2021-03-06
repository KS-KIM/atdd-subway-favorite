package wooteco.subway.acceptance.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wooteco.subway.AcceptanceTest;
import wooteco.subway.service.member.dto.MemberResponse;
import wooteco.subway.service.member.dto.TokenResponse;

public class MemberAcceptanceTest extends AcceptanceTest {
    @DisplayName("회원 가입, 조회, 탈퇴")
    @Test
    void manageMember() {
        String location = createMember(TEST_USER_EMAIL, TEST_USER_NAME, TEST_USER_PASSWORD);
        assertThat(location).isNotBlank();

        TokenResponse tokenResponse = login(TEST_USER_EMAIL, TEST_USER_PASSWORD);

        MemberResponse memberResponse = getMember(TEST_USER_EMAIL, tokenResponse);
        assertThat(memberResponse.getId()).isNotNull();
        assertThat(memberResponse.getEmail()).isEqualTo(TEST_USER_EMAIL);
        assertThat(memberResponse.getName()).isEqualTo(TEST_USER_NAME);

        updateMember(memberResponse, tokenResponse);
        MemberResponse updatedMember = getMember(TEST_USER_EMAIL, tokenResponse);
        assertThat(updatedMember.getName()).isEqualTo("NEW_" + TEST_USER_NAME);

        deleteMember(memberResponse, tokenResponse);
    }
}
