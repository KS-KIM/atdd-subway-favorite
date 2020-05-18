package wooteco.subway.acceptance.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wooteco.subway.AcceptanceTest;
import wooteco.subway.service.member.dto.MemberResponse;
import wooteco.subway.service.member.dto.TokenResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginMemberAcceptanceTest extends AcceptanceTest {

    @DisplayName("로그인 회원 관련 기능")
    @Test
    void manageMember() {
        String location = createMember(TEST_USER_EMAIL, TEST_USER_NAME, TEST_USER_PASSWORD);
        assertThat(location).isNotBlank();

        TokenResponse tokenResponse = login(TEST_USER_EMAIL, TEST_USER_PASSWORD);

        MemberResponse memberResponse = getMember(tokenResponse);
        assertThat(memberResponse.getId()).isNotNull();
        assertThat(memberResponse.getEmail()).isEqualTo(TEST_USER_EMAIL);
        assertThat(memberResponse.getName()).isEqualTo(TEST_USER_NAME);

        updateMember(tokenResponse);
        MemberResponse updatedMember = getMember(tokenResponse);
        assertThat(updatedMember.getName()).isEqualTo("NEW_" + TEST_USER_NAME);

        deleteMember(tokenResponse);
    }


}