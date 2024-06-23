package pe.joedayz.training.conference.session;


import pe.joedayz.training.conference.speaker.Speaker;

public class SessionWithSpeaker {

    public Long id;
    public int schedule;
    public Speaker speaker;

    public SessionWithSpeaker( Long id, int schedule, Speaker speaker ) {
        this.id = id;
        this.schedule = schedule;
        this.speaker = speaker;
    }
}
