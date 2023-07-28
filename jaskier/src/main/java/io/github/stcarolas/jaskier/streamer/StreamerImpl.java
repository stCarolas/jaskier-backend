package io.github.stcarolas.jaskier.streamer;

import org.immutables.criteria.Criteria;
import org.immutables.criteria.repository.sync.SyncRepository;
import org.immutables.value.Value;

import io.github.stcarolas.audd.api.AudDClient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@Criteria
@Criteria.Repository
@JsonSerialize(as = ImmutableStreamerImpl.class)
@JsonDeserialize(as = ImmutableStreamerImpl.class)
public abstract class StreamerImpl extends Streamer {

    @Criteria.Id
    public abstract Integer id();

    public void save(SyncRepository.Writable<StreamerImpl> repository) {
        repository.upsert(this);
    }

    public void delete(
        SyncRepository.Writable<StreamerImpl> repository,
        AudDClient audDClient
    ) {
        // todo transaction support
        repository.delete(StreamerImplCriteria.streamerImpl.id.is(id()));
        audDClient.deleteStream(id());
    }
}
