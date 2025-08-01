package org.example.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentSubjectId implements Serializable {

    private Long studentId;
    private Long subjectId;

    // equals() và hashCode() là bắt buộc
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentSubjectId)) return false;
        StudentSubjectId that = (StudentSubjectId) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId);
    }
    //Nếu không override, các entity sẽ bị so sánh theo địa chỉ bộ nhớ (mặc định của Object), dẫn đến lỗi logic khi persist, merge, hoặc remove.


    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }
}
